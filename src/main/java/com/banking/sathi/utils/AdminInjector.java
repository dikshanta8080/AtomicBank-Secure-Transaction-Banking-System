package com.banking.sathi.utils;

import com.banking.sathi.dao.UserDao;
import com.banking.sathi.enums.Role;
import com.banking.sathi.enums.UserStatus;
import com.banking.sathi.model.User;
import com.banking.sathi.repository.UserRepository;
import com.banking.sathi.service.AuthService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.mindrot.jbcrypt.BCrypt;

import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class AdminInjector implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(AdminInjector.class.getName());
    UserRepository userRepository = new UserDao();
    AuthService authService = new AuthService();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            if (!userRepository.existsByRoleAdmin()) {

                User user = new User();
                user.setName("Dikshanta Acharya");
                user.setEmail("dikshantaacharya04@gmail.com");
                user.setRole(Role.ADMIN);
                user.setUserStatus(UserStatus.ACTIVE);

                String encodedPassword =
                        BCrypt.hashpw(
                                "@Dikshyant9898",
                                BCrypt.gensalt(11)
                        );

                user.setPassword(encodedPassword);

                int saveUser = userRepository.saveUser(
                        user,
                        DbConnection.getConnection()
                );

                System.out.println("Admin created: " + saveUser);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to inject the admin due to database error {e}", e);
        }
    }


}
