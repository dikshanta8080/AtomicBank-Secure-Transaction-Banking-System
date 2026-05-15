<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Interest Summary — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/interest.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/user-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>Interest Summary</h2>
            </div>
        </div>

        <div class="layout-grid two">
            <div class="card hero-card">
                <div class="hero-label">Estimated Interest Earned</div>
                <div class="hero-value">NPR <fmt:formatNumber value="${interestAmount}" pattern="#,##0.00"/></div>
                <div class="hero-meta">
                    <span>Annual Rate: ${interestRate}%</span>
                    <span>Simple Interest</span>
                </div>
            </div>
            <div class="card">
                <div class="section-header"><h3>Apply Interest to Account</h3></div>
                <p style="color:#4b6b5a;font-size:14px;margin-bottom:20px;">
                    Applies calculated interest to your balance. This cannot be undone.
                </p>
                <form action="${pageContext.request.contextPath}/interest" method="post">
                    <button type="submit" class="btn btn-primary">Apply Interest Now</button>
                </form>
                <c:if test="${not empty success}"><div class="alert alert-success" style="margin-top:16px;margin-bottom:0;">${success}</div></c:if>
                <c:if test="${not empty error}"><div class="alert alert-danger" style="margin-top:16px;margin-bottom:0;">${error}</div></c:if>
            </div>
        </div>

        <div class="card" style="margin-top:20px;">
            <div class="section-header"><h3>How Interest Is Calculated</h3></div>
            <div class="info-list">
                <div class="info-row">
                    <span class="info-key">Formula</span>
                    <span class="info-val">Simple Interest = (Principal × Rate × Time) / 100</span>
                </div>
                <div class="info-row">
                    <span class="info-key">Principal</span>
                    <span class="info-val">Current account balance</span>
                </div>
                <div class="info-row">
                    <span class="info-key">Annual Rate</span>
                    <span class="info-val">${interestRate}% per annum</span>
                </div>
                <div class="info-row">
                    <span class="info-key">Time</span>
                    <span class="info-val">1 year (estimated)</span>
                </div>
                <div class="info-row">
                    <span class="info-key">Estimated Interest</span>
                    <span class="info-val" style="color:#059669;font-weight:700;">NPR <fmt:formatNumber value="${interestAmount}" pattern="#,##0.00"/></span>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
