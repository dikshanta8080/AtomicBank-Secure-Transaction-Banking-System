<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account — Atomic Bank</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/register.css">
</head>
<body>

<div class="left-panel">
    <div>
        <div class="logo">
            <div class="logo-mark">A</div>
            <div class="logo-name">Atomic Bank</div>
        </div>
        <h1 class="brand-headline">Join<br>Atomic<br><em>Bank.</em></h1>
        <p class="brand-sub">Open your account in minutes. Secure transactions, credit cards, and interest savings — all in one place.</p>
    </div>
    <div class="features">
        <div class="feature-item">
            <div class="feature-icon">
                <svg viewBox="0 0 24 24"><path d="M12 2L2 7l10 5 10-5-10-5z"/><path d="M2 17l10 5 10-5"/><path d="M2 12l10 5 10-5"/></svg>
            </div>
            <span class="feature-text">Savings and current account options</span>
        </div>
        <div class="feature-item">
            <div class="feature-icon">
                <svg viewBox="0 0 24 24"><rect x="1" y="4" width="22" height="16" rx="2"/><line x1="1" y1="10" x2="23" y2="10"/></svg>
            </div>
            <span class="feature-text">Apply for credit and debit cards instantly</span>
        </div>
        <div class="feature-item">
            <div class="feature-icon">
                <svg viewBox="0 0 24 24"><line x1="12" y1="1" x2="12" y2="23"/><path d="M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6"/></svg>
            </div>
            <span class="feature-text">Earn interest on your savings balance</span>
        </div>
    </div>
</div>

<div class="right-panel">
    <div class="form-container">
        <div class="form-eyebrow">Get Started</div>
        <h2 class="form-title">Create your account</h2>
        <p class="form-subtitle">Already have an account? <a href="<%= request.getContextPath() %>/login">Sign in</a></p>

        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>

        <form action="<%= request.getContextPath() %>/register" method="post">
            <div class="form-grid-2">
                <div class="field">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" placeholder="John Doe"
                           value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>"
                           required>
                </div>
                <div class="field">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" name="email" placeholder="you@example.com"
                           value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>"
                           autocomplete="email" required>
                </div>
            </div>
            <div class="form-grid-2">
                <div class="field">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Min 8 characters"
                           autocomplete="new-password" required>
                </div>
                <div class="field">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Repeat password"
                           autocomplete="new-password" required>
                </div>
            </div>
            <button type="submit" class="submit-btn">Create Account</button>
        </form>

        <p class="footer-note">By signing up you agree to Atomic Bank's Terms of Service. &copy; 2025 Atomic Bank.</p>
    </div>
</div>

</body>
</html>
