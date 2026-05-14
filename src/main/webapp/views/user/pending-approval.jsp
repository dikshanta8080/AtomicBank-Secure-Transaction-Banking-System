<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Pending Approval — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pending-approval.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/user-nav.jspf" %>
    <main class="workspace status-page">
        <div class="status-center">
            <div class="status-icon">⏳</div>
            <h2 class="status-title">Account Under Review</h2>
            <p class="status-description">
                Your account request has been submitted. Our team is reviewing your KYC details
                and will activate your account shortly.
            </p>

            <div class="status-info-box">
                <div class="info-row">
                    <span class="info-key">Account Number</span>
                    <span class="info-val">${not empty accountNumber ? accountNumber : '—'}</span>
                </div>
                <div class="info-row">
                    <span class="info-key">Temporary Transaction PIN</span>
                    <span class="info-val">${not empty transactionPin ? transactionPin : '—'}</span>
                </div>
            </div>

            <div class="status-warning">
                <strong>Important:</strong> Save your temporary transaction PIN securely.
                You will need it to confirm deposits, withdrawals, and transfers once your account is activated.
            </div>

            <div class="status-actions">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/UserDashboard">Return to Dashboard</a>
            </div>
        </div>
    </main>
</div>
</body>
</html>
