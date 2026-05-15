<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transfer — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/transfer.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/user-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>Transfer Funds</h2>
            </div>
        </div>

        <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

        <div class="layout-grid two">
            <div class="card">
                <div class="section-header"><h3>Send Money</h3></div>
                <form action="${pageContext.request.contextPath}/transfer" method="post">
                    <div class="field">
                        <label for="toUserId">Transfer To</label>
                        <select id="toUserId" name="toUserId" required>
                            <option value="" disabled selected>Select recipient...</option>
                            <c:forEach items="${targets}" var="target">
                                <option value="${target.userId}">${target.userName} — ${target.accountNumber}</option>
                            </c:forEach>
                        </select>
                        <span class="field-hint">Only active accounts are shown.</span>
                    </div>
                    <div class="field">
                        <label for="amount">Amount (NPR)</label>
                        <input type="number" id="amount" name="amount" placeholder="e.g. 1000" min="1" step="0.01" required>
                    </div>
                    <div class="field">
                        <label for="transactionPin">Transaction PIN</label>
                        <input type="password" id="transactionPin" name="transactionPin" placeholder="Enter your PIN" maxlength="10" required>
                    </div>
                    <button type="submit" class="btn btn-primary" style="width:100%;margin-top:4px;">Confirm Transfer</button>
                </form>
            </div>

            <div class="card">
                <div class="section-header"><h3>Your Account</h3></div>
                <c:choose>
                    <c:when test="${account != null}">
                        <div class="info-list">
                            <div class="info-row">
                                <span class="info-key">Account Number</span>
                                <span class="info-val">${account.accountNumber}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key">Account Type</span>
                                <span class="info-val">${account.type}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key">Status</span>
                                <span class="info-val">
                                    <span class="badge ${account.status == 'ACTIVE' ? 'badge-success' : 'badge-warning'}">${account.status}</span>
                                </span>
                            </div>
                            <div class="info-row">
                                <span class="info-key">Available Balance</span>
                                <span class="info-val" style="color:#059669;font-size:18px;">NPR <fmt:formatNumber value="${account.balance}" pattern="#,##0.00"/></span>
                            </div>
                        </div>
                        <div class="alert alert-info" style="margin-top:16px;margin-bottom:0;">
                            Transfers are instant and cannot be reversed. Double-check the recipient before confirming.
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="empty-state"><p>No account found.</p></div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
</div>
</body>
</html>
