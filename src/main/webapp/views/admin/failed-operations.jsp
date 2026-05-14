<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Failed Operations — Atomic Bank Admin</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/failed-operations.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/admin-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>Failed Operations</h2>
                <p>All rolled-back and failed transactions. Use this to investigate system issues.</p>
            </div>
            <div class="topbar-actions">
                <a class="btn btn-ghost" href="${pageContext.request.contextPath}/admin/transactions">All Transactions</a>
            </div>
        </div>

        <div class="card">
            <div class="section-header">
                <h3>Rolled Back &amp; Failed Transactions</h3>
                <c:if test="${not empty failedTransactions}">
                    <span class="badge badge-danger">Failed Records Found</span>
                </c:if>
            </div>
            <c:choose>
                <c:when test="${empty failedTransactions}">
                    <div class="all-clear-state">
                        <div class="check">✅</div>
                        <p>No failed operations found. System is running smoothly.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrap">
                        <table>
                            <thead>
                            <tr>
                                <th>Reference</th>
                                <th>Type</th>
                                <th>From Account</th>
                                <th>To Account</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Failure Reason</th>
                                <th>Date</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${failedTransactions}" var="tx">
                                <tr>
                                    <td><strong>${tx.reference}</strong></td>
                                    <td>${tx.type}</td>
                                    <td>${not empty tx.fromAccountNumber ? tx.fromAccountNumber : '—'}</td>
                                    <td>${not empty tx.toAccountNumber ? tx.toAccountNumber : '—'}</td>
                                    <td class="failed-amount-cell">NPR <fmt:formatNumber value="${tx.amount}" pattern="#,##0.00"/></td>
                                    <td>
                                        <span class="badge ${tx.status == 'ROLLED_BACK' ? 'badge-warning' : 'badge-danger'}">${tx.status}</span>
                                    </td>
                                    <td class="failed-reason-cell">${tx.remarks}</td>
                                    <td class="failed-date-cell">${tx.created}</td>
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
