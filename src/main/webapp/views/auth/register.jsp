<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@0;1&family=DM+Sans:wght@300;400;500;600&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/register.css">
</head>
<body>

<!-- Left Panel -->
<div class="left-panel">
    <div>
        <div class="logo">
            <div class="logo-mark">A</div>
            <div class="logo-name">Atomic <span>Bank</span></div>
        </div>
        <h1 class="brand-headline">
            Banking built<br>for <em>modern</em><br>living.
        </h1>
        <p class="brand-sub">
            Secure, intelligent, and always at your fingertips.
            Join thousands who trust Atomic Bank with their financial future.
        </p>
    </div>

    <div class="features">
        <div class="feature-item">
            <div class="feature-icon">
                <svg viewBox="0 0 24 24">
                    <rect x="3" y="11" width="18" height="11" rx="2"/>
                    <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                </svg>
            </div>
            <span class="feature-text">Bank-grade 256-bit encryption on all transactions</span>
        </div>
        <div class="feature-item">
            <div class="feature-icon">
                <svg viewBox="0 0 24 24">
                    <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/>
                </svg>
            </div>
            <span class="feature-text">FDIC insured deposits up to $250,000</span>
        </div>
        <div class="feature-item">
            <div class="feature-icon">
                <svg viewBox="0 0 24 24">
                    <circle cx="12" cy="12" r="10"/>
                    <path d="M12 6v6l4 2"/>
                </svg>
            </div>
            <span class="feature-text">24/7 real-time monitoring and instant alerts</span>
        </div>
    </div>
</div>

<!-- Right Panel -->
<div class="right-panel">
    <div class="form-container">

        <div class="form-eyebrow">New Account</div>
        <h2 class="form-title">Create your account</h2>
        <p class="form-subtitle">
            Already a member?
            <a href="<%= request.getContextPath() %>/login">Sign in instead</a>
        </p>

        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <% if (request.getAttribute("success") != null) { %>
        <div class="alert alert-success">
            <%= request.getAttribute("success") %>
        </div>
        <% } %>

        <form action="<%= request.getContextPath() %>/register" method="post">

            <div class="field">
                <label for="name">Full Name</label>
                <input
                        type="text"
                        id="name"
                        name="name"
                        placeholder="e.g. Ram Bahadur Thapa"
                        value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>"
                        autocomplete="name"
                        required
                >
            </div>

            <div class="field">
                <label for="email">Email Address</label>
                <input
                        type="email"
                        id="email"
                        name="email"
                        placeholder="you@example.com"
                        value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>"
                        autocomplete="email"
                        required
                >
            </div>

            <div class="field">
                <label for="password">Password</label>
                <input
                        type="password"
                        id="password"
                        name="password"
                        placeholder="Min. 8 characters"
                        autocomplete="new-password"
                        required
                >
            </div>

            <button type="submit">Create Account</button>

        </form>

        <p class="footer-note">
            By registering, you agree to our <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a>.
        </p>

    </div>
</div>

</body>
</html>
