<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Apply for Card — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/apply-card.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/user-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>Apply for a Card</h2>
                <p>Submit a credit or debit card application for admin review.</p>
            </div>
        </div>

        <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

        <div class="layout-grid two">
            <div class="card">
                <div class="section-header"><h3>Card Application</h3></div>
                <form action="${pageContext.request.contextPath}/cards/apply" method="post">
                    <div class="field">
                        <label for="type">Card Type</label>
                        <select id="type" name="type" required>
                            <option value="" disabled selected>Select card type...</option>
                            <option value="CREDIT">Credit Card</option>
                            <option value="DEBIT">Debit Card</option>
                        </select>
                    </div>
                    <div class="field">
                        <label for="creditLimit">Requested Credit Limit (NPR)</label>
                        <input type="number" id="creditLimit" name="creditLimit" placeholder="e.g. 50000" min="1" step="0.01" required>
                        <span class="field-hint">Eligibility: up to 4x your monthly income. Monthly income: NPR <fmt:formatNumber value="${monthlyIncome}" pattern="#,##0.00"/></span>
                    </div>
                    <button type="submit" class="btn btn-primary" style="width:100%;margin-top:4px;">Submit Application</button>
                </form>
            </div>

            <div class="card">
                <div class="section-header"><h3>Eligibility Info</h3></div>
                <div class="info-list">
                    <div class="info-row">
                        <span class="info-key">Monthly Income</span>
                        <span class="info-val">NPR <fmt:formatNumber value="${monthlyIncome}" pattern="#,##0.00"/></span>
                    </div>
                    <div class="info-row">
                        <span class="info-key">Max Credit Limit (4x Monthly)</span>
                        <span class="info-val">NPR <fmt:formatNumber value="${monthlyIncome * 4}" pattern="#,##0.00"/></span>
                    </div>
                </div>
                <div class="alert alert-info" style="margin-top:16px;margin-bottom:0;">
                    Your application will be reviewed by an admin. Card issuance includes a defined credit limit and expiry date.
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
