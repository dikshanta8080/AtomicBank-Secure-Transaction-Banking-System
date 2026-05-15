<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transactions — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/transactions.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/user-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>My Transactions</h2>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/transactions" method="get" style="display:block;">
            <div class="filter-bar">
                <div class="field">
                    <label for="fromDate">From Date</label>
                    <input type="date" id="fromDate" name="fromDate" value="${param.fromDate}">
                </div>
                <div class="field">
                    <label for="toDate">To Date</label>
                    <input type="date" id="toDate" name="toDate" value="${param.toDate}">
                </div>
                <div class="filter-actions">
                    <button type="submit" class="btn btn-primary">Filter</button>
                    <a class="btn btn-ghost" href="${pageContext.request.contextPath}/transactions">Reset</a>
                </div>
            </div>
        </form>

        <div class="card">
            <div class="section-header">
                <h3>Transaction History</h3>
            </div>
            <c:choose>
                <c:when test="${empty transactions}">
                    <div class="no-tx-state">
                        <p>No transactions found for the selected period.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrap">
                        <table>
                            <thead>
                            <tr>
                                <th>Reference</th>
                                <th>Type</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Remarks</th>
                                <th>Date &amp; Time</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${transactions}" var="tx">
                                <tr>
                                    <td><strong>${tx.reference}</strong></td>
                                    <td>${tx.type}</td>
                                    <td>${not empty tx.fromAccountNumber ? tx.fromAccountNumber : '—'}</td>
                                    <td>${not empty tx.toAccountNumber ? tx.toAccountNumber : '—'}</td>
                                    <td class="${tx.type == 'DEPOSIT' ? 'amount-credit' : 'amount-debit'}">
                                        NPR <fmt:formatNumber value="${tx.amount}" pattern="#,##0.00"/>
                                    </td>
                                    <td>
                                        <span class="badge ${tx.status == 'SUCCESS' ? 'badge-success' : tx.status == 'ROLLED_BACK' ? 'badge-warning' : 'badge-danger'}">${tx.status}</span>
                                    </td>
                                    <td class="tx-remarks-cell">${tx.remarks}</td>
                                    <td class="tx-date-cell">${tx.created}</td>
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
