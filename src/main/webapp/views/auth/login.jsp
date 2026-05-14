<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In — Atomic Bank</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
</head>
<body>

<div class="left-panel">
    <div>
        <div class="logo">
            <div class="logo-mark">A</div>
            <div class="logo-name">Atomic <span>Bank</span></div>
        </div>
        <h1 class="brand-headline">Welcome<br>back to<br><em>Atomic.</em></h1>
        <p class="brand-sub">Your finances, secured and always within reach. Sign in to access your accounts, transfers, and more.</p>
    </div>
    <div class="features">
        <div class="feature-item">
            <div class="feature-icon">
                <svg viewBox="0 0 24 24"><rect x="3" y="11" width="18" height="11" rx="2"/><path d="M7 11V7a5 5 0 0 1 10 0v4"/></svg>
            </div>
            <span class="feature-text">Bank-grade security on every transaction</span>
        </div>
        <div class="feature-item">
            <div class="feature-icon">
                <svg viewBox="0 0 24 24"><polyline points="22 12 18 12 15 21 9 3 6 12 2 12"/></svg>
            </div>
            <span class="feature-text">Real-time balance and transaction updates</span>
        </div>
        <div class="feature-item">
            <div class="feature-icon">
                <svg viewBox="0 0 24 24"><circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/></svg>
            </div>
            <span class="feature-text">24/7 access from anywhere in the world</span>
        </div>
    </div>
</div>

<div class="right-panel">
    <div class="form-container">
        <div class="form-eyebrow">Customer Portal</div>
        <h2 class="form-title">Sign in to your account</h2>
        <p class="form-subtitle">Don't have an account? <a href="<%= request.getContextPath() %>/register">Create one free</a></p>

        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>

        <%
            jakarta.servlet.http.HttpSession flashSession = request.getSession(false);
            String successMsg = null;
            if (flashSession != null) {
                successMsg = (String) flashSession.getAttribute("success");
                if (successMsg != null) flashSession.removeAttribute("success");
            }
        %>
        <% if (successMsg != null) { %>
        <div class="alert alert-success"><%= successMsg %></div>
        <% } %>

        <form action="<%= request.getContextPath() %>/login" method="post">
            <div class="field">
                <label for="email">Email Address</label>
                <input type="email" id="email" name="email" placeholder="you@example.com"
                       value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>"
                       autocomplete="email" required>
            </div>
            <div class="field">
                <div class="field-header">
                    <label for="password">Password</label>
                    <a href="#" class="forgot-link">Forgot password?</a>
                </div>
                <input type="password" id="password" name="password" placeholder="Enter your password"
                       autocomplete="current-password" required>
            </div>
            <button type="submit" class="submit-btn">Sign In</button>
        </form>

        <p class="footer-note">Protected by Atomic Bank security. &copy; 2025 Atomic Bank. All rights reserved.</p>
    </div>
</div>

</body>
</html>
