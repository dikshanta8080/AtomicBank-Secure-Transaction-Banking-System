<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-dashboard.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/admin-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>Admin Dashboard</h2>
                <p>System overview — approvals, balances, and transaction integrity.</p>
            </div>
        </div>

        <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

        <div class="stats">
            <div class="stat">
                <div class="stat-label">Total Accounts</div>
                <div class="stat-value">${totalAccounts}</div>
                <div class="stat-sub">Registered bank accounts</div>
            </div>
            <div class="stat">
                <div class="stat-label">Pending Approvals</div>
                <div class="stat-value">${pendingApprovals}</div>
                <div class="stat-sub">Awaiting KYC review</div>
            </div>
            <div class="stat">
                <div class="stat-label">Total Deposits</div>
                <div class="stat-value">NPR <fmt:formatNumber value="${totalDeposits}" pattern="#,##0"/></div>
                <div class="stat-sub">Across all accounts</div>
            </div>
            <div class="stat">
                <div class="stat-label">Rolled Back</div>
                <div class="stat-value">${rolledBackCount}</div>
                <div class="stat-sub">Failed operations logged</div>
            </div>
        </div>

        <div class="layout-grid two" style="margin-top:24px;">
            <div class="card">
                <div class="section-header">
                    <h3>Pending Account Approvals</h3>
                    <a class="btn btn-ghost btn-sm" href="${pageContext.request.contextPath}/admin/accounts">Open queue</a>
                </div>
                <c:choose>
                    <c:when test="${empty pendingAccounts}">
                        <div class="empty-state"><p>No pending accounts.</p></div>
                    </c:when>
                    <c:otherwise>
                        <div class="table-wrap">
                            <table>
                                <thead>
                                <tr><th>Name</th><th>Email</th><th>Phone</th><th>Type</th><th>Status</th></tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pendingAccounts}" var="acc">
                                    <tr>
                                        <td>${acc.name}</td>
                                        <td>${acc.email}</td>
                                        <td>${acc.phone}</td>
                                        <td>${acc.accountType}</td>
                                        <td><span class="badge badge-warning">${acc.accountStatus}</span></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="card">
                <div class="section-header">
                    <h3>Pending Card Applications</h3>
                    <a class="btn btn-ghost btn-sm" href="${pageContext.request.contextPath}/admin/cards">Open queue</a>
                </div>
                <div class="info-list" style="margin-bottom:16px;">
                    <div class="info-row">
                        <span class="info-key">Total Cards</span>
                        <span class="info-val">${totalCards}</span>
                    </div>
                    <div class="info-row">
                        <span class="info-key">Pending Approvals</span>
                        <span class="info-val">${pendingCardCount}</span>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${empty pendingCards}">
                        <div class="empty-state"><p>No pending card applications.</p></div>
                    </c:when>
                    <c:otherwise>
                        <div class="table-wrap">
                            <table>
                                <thead>
                                <tr><th>Card ID</th><th>Account ID</th><th>Type</th><th>Req. Limit</th></tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pendingCards}" var="card">
                                    <tr>
                                        <td>#${card.id}</td>
                                        <td>${card.accountId}</td>
                                        <td>${card.type}</td>
                                        <td>NPR <fmt:formatNumber value="${card.creditLimit}" pattern="#,##0"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="layout-grid two" style="margin-top:20px;">
            <div class="card">
                <div class="section-header"><h3>Quick Links</h3></div>
                <div class="actions">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/accounts">Manage Accounts</a>
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/cards">Card Applications</a>
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/transactions">All Transactions</a>
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/admin/failed-operations">Failed Ops</a>
                </div>
            </div>
            <div class="card">
                <div class="section-header"><h3>System Health</h3></div>
                <div class="info-list">
                    <div class="info-row">
                        <span class="info-key">Total Accounts</span>
                        <span class="info-val badge badge-info">${totalAccounts}</span>
                    </div>
                    <div class="info-row">
                        <span class="info-key">Pending KYC Reviews</span>
                        <span class="info-val badge badge-warning">${pendingApprovals}</span>
                    </div>
                    <div class="info-row">
                        <span class="info-key">Pending Card Approvals</span>
                        <span class="info-val badge badge-warning">${pendingCardCount}</span>
                    </div>
                    <div class="info-row">
                        <span class="info-key">Rolled Back Transactions</span>
                        <span class="info-val badge badge-danger">${rolledBackCount}</span>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
