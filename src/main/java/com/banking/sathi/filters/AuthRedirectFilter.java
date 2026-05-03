package com.banking.sathi.filters;

import com.banking.sathi.dao.AccountDao;
import com.banking.sathi.enums.AccountStatus;
import com.banking.sathi.enums.Role;
import com.banking.sathi.model.Account;
import com.banking.sathi.model.User;
import com.banking.sathi.repository.AccountRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebFilter("/*")
public class AuthRedirectFilter implements Filter {

    AccountRepository accountRepository = new AccountDao();

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String contextPath = request.getContextPath();
        String path = request.getRequestURI().substring(contextPath.length());

        if (path.startsWith("/login") ||
                path.startsWith("/register") ||
                path.startsWith("/assets/") ||
                path.startsWith("/css/") ||
                path.startsWith("/js/")) {

            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(contextPath + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");

        if (user.getRole() == Role.ADMIN) {
            if (!path.startsWith("/AdminDashboard")) {
                response.sendRedirect(contextPath + "/AdminDashboard");
                return;
            }
            filterChain.doFilter(request, response);
            return;
        }


        if (path.startsWith("/AdminDashboard")) {
            response.sendRedirect(contextPath + "/UserDashboard");
            return;
        }

        Optional<Account> accountOpt =
                accountRepository.findByUserId(user.getId());

        if (accountOpt.isEmpty()) {
            if (!path.startsWith("/account")) {
                response.sendRedirect(contextPath + "/account");
                return;
            }
            filterChain.doFilter(request, response);
            return;
        }

        Account account = accountOpt.get();

        if (account.getStatus() == AccountStatus.INACTIVE) {
            if (!path.startsWith("/PendingApproval")) {
                response.sendRedirect(contextPath + "/PendingApproval");
                return;
            }
            filterChain.doFilter(request, response);
            return;
        }

        if (account.getStatus() == AccountStatus.FROZEN) {
            if (!path.startsWith("/AccountFrozen")) {
                response.sendRedirect(contextPath + "/AccountFrozen");
                return;
            }
            filterChain.doFilter(request, response);
            return;
        }
        
        if (!path.startsWith("/UserDashboard")) {
            response.sendRedirect(contextPath + "/UserDashboard");
            return;
        }

        filterChain.doFilter(request, response);
    }
}