<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Transactions — Atomic Bank Admin</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-transactions.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/admin-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>All Transactions</h2>
                <p>Complete audit trail of every transaction across all accounts.</p>
            </div>
            <div class="topbar-actions">
                <a class="btn btn-warning" href="${pageContext.request.contextPath}/admin/failed-operations">View Failed Ops</a>
            </div>
        </div>

        <div class="card">
            <c:choose>
                <c:when test="${empty transactions}">
                    <div class="empty-state"><p>No transactions recorded yet.</p></div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrap">
                        <table>
                            <thead>
                            <tr>
                                <th>Reference</th><th>Type</th><th>From User</th><th>From Acct</th>
                                <th>To User</th><th>To Acct</th><th>Amount</th><th>Status</th><th>Date</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${transactions}" var="tx">
                                <tr>
                                    <td><strong>${tx.reference}</strong></td>
                                    <td>${tx.type}</td>
                                    <td>${not empty tx.fromUserName ? tx.fromUserName : '—'}</td>
                                    <td>${not empty tx.fromAccountNumber ? tx.fromAccountNumber : '—'}</td>
                                    <td>${not empty tx.toUserName ? tx.toUserName : '—'}</td>
                                    <td>${not empty tx.toAccountNumber ? tx.toAccountNumber : '—'}</td>
                                    <td class="${tx.type == 'DEPOSIT' ? 'amount-credit' : 'amount-debit'}">NPR <fmt:formatNumber value="${tx.amount}" pattern="#,##0.00"/></td>
                                    <td><span class="badge ${tx.status == 'SUCCESS' ? 'badge-success' : tx.status == 'ROLLED_BACK' ? 'badge-warning' : 'badge-danger'}">${tx.status}</span></td>
                                    <td style="white-space:nowrap;">${tx.created}</td>
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
