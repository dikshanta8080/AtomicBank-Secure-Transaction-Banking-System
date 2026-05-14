<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Frozen — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/account-frozen.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/user-nav.jspf" %>
    <main class="workspace frozen-page">
        <div class="frozen-center">
            <div class="frozen-icon">🔒</div>
            <h2 class="frozen-title">Account Frozen</h2>
            <p class="frozen-description">
                Your account has been temporarily frozen by the administrator.
                You cannot perform any transactions at this time.
            </p>
            <div class="frozen-contact-box">
                <strong>What to do:</strong> Contact Atomic Bank support at
                <strong>support@atomicbank.com</strong> or visit your nearest branch to resolve this issue.
            </div>
            <div class="frozen-actions">
                <a class="btn btn-ghost" href="${pageContext.request.contextPath}/logout">Sign Out</a>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/UserDashboard">Back to Dashboard</a>
            </div>
        </div>
    </main>
</div>
</body>
</html>
