<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Accounts — Atomic Bank Admin</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-accounts.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/admin-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>Manage Accounts</h2>
                <p>Review KYC applications, approve, reject, or freeze customer accounts.</p>
            </div>
        </div>

        <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

        <div class="card accounts-pending-section">
            <div class="section-header">
                <h3>Pending Approval Queue</h3>
                <c:if test="${not empty pendingAccounts}">
                    <span class="badge badge-warning">Pending</span>
                </c:if>
            </div>
            <c:choose>
                <c:when test="${empty pendingAccounts}">
                    <div class="empty-state"><p>No pending account applications.</p></div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrap">
                        <table>
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>KYC Status</th>
                                <th>Account Type</th>
                                <th>Account Status</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pendingAccounts}" var="acc">
                                <tr>
                                    <td class="account-name-cell">${acc.name}</td>
                                    <td>${acc.email}</td>
                                    <td>${acc.phone}</td>
                                    <td><span class="badge badge-neutral">${acc.kycStatus}</span></td>
                                    <td>${acc.accountType}</td>
                                    <td><span class="badge badge-warning">${acc.accountStatus}</span></td>
                                    <td>
                                        <div class="inline-action-row">
                                            <a class="btn btn-ghost btn-sm" href="${pageContext.request.contextPath}/admin/accounts/review?userId=${acc.userId}">Review KYC</a>
                                            <form action="${pageContext.request.contextPath}/admin/accounts" method="post">
                                                <input type="hidden" name="userId" value="${acc.userId}">
                                                <input type="hidden" name="action" value="approve">
                                                <button class="btn btn-success btn-sm" type="submit">Approve</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/admin/accounts" method="post">
                                                <input type="hidden" name="userId" value="${acc.userId}">
                                                <input type="hidden" name="action" value="reject">
                                                <button class="btn btn-danger btn-sm" type="submit">Reject</button>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="card all-accounts-section" style="margin-top:24px;">
            <div class="section-header">
                <h3>All Customer Accounts</h3>
            </div>
            <c:choose>
                <c:when test="${empty allAccounts}">
                    <div class="empty-state"><p>No accounts in the system.</p></div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrap">
                        <table>
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Account Number</th>
                                <th>Type</th>
                                <th>Balance</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${allAccounts}" var="row">
                                <tr>
                                    <td class="account-name-cell">${row.userName}</td>
                                    <td>${row.email}</td>
                                    <td><span class="account-number-code">${row.accountNumber}</span></td>
                                    <td>${row.accountType}</td>
                                    <td class="balance-cell">NPR <fmt:formatNumber value="${row.balance}" pattern="#,##0.00"/></td>
                                    <td>
                                        <span class="badge ${row.accountStatus == 'ACTIVE' ? 'badge-success' : row.accountStatus == 'INACTIVE' ? 'badge-neutral' : 'badge-danger'}">${row.accountStatus}</span>
                                    </td>
                                    <td>
                                        <c:if test="${row.accountStatus == 'ACTIVE'}">
                                            <form action="${pageContext.request.contextPath}/admin/accounts" method="post">
                                                <input type="hidden" name="userId" value="${row.userId}">
                                                <input type="hidden" name="action" value="freeze">
                                                <button class="btn btn-danger btn-sm" type="submit">Freeze</button>
                                            </form>
                                        </c:if>
                                        <c:if test="${row.accountStatus != 'ACTIVE'}">
                                            <span style="color:#9aafc7;font-size:13px;">—</span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</div>
</body>
</html>
