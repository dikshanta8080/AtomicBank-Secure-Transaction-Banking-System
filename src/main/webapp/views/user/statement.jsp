<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Statement — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/statement.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/user-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>Account Statement</h2>
            </div>
            <div class="topbar-actions no-print">
                <button onclick="window.print()" class="btn btn-secondary">Print / Export PDF</button>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/statement" method="get" style="display:block;" class="no-print">
            <div class="filter-bar">
                <div class="field">
                    <label for="fromDate">From Date</label>
                    <input type="date" id="fromDate" name="fromDate" value="${param.fromDate}">
                </div>
                <div class="field">
                    <label for="toDate">To Date</label>
                    <input type="date" id="toDate" name="toDate" value="${param.toDate}">
                </div>
                <div style="display:flex;gap:8px;align-items:flex-end;">
                    <button type="submit" class="btn btn-primary">Generate</button>
                    <a class="btn btn-ghost" href="${pageContext.request.contextPath}/statement">Reset</a>
                </div>
            </div>
        </form>

        <div class="card">
            <div style="display:flex;justify-content:space-between;align-items:flex-start;margin-bottom:24px;padding-bottom:20px;border-bottom:1px solid #d6e3f5;">
                <div>
                    <div style="font-size:22px;font-weight:800;color:#0f1d2e;">ATOMIC BANK</div>
                    <div style="font-size:13px;color:#5a6e8a;margin-top:4px;">Official Account Statement</div>
                </div>
                <div style="text-align:right;">
                    <div style="font-size:13px;color:#5a6e8a;">Account Holder</div>
                    <div style="font-size:16px;font-weight:700;">${sessionScope.user.name}</div>
                    <c:if test="${account != null}">
                        <div style="font-size:13px;color:#5a6e8a;margin-top:4px;">${account.accountNumber} · ${account.type}</div>
                        <div style="font-size:13px;color:#5a6e8a;">Balance: NPR <fmt:formatNumber value="${account.balance}" pattern="#,##0.00"/></div>
                    </c:if>
                </div>
            </div>

            <div class="section-header">
                <h3>Transactions</h3>
                <c:if test="${not empty transactions}"><span style="font-size:13px;color:#5a6e8a;">Statement results</span></c:if>
            </div>
            <c:choose>
                <c:when test="${empty transactions}">
                    <div class="empty-state"><p>No transactions found. Use the filter above to generate a statement.</p></div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrap">
                        <table>
                            <thead>
                            <tr>
                                <th>Reference</th>
                                <th>Date</th>
                                <th>Type</th>
                                <th>From Account</th>
                                <th>To Account</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Remarks</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${transactions}" var="tx">
                                <tr>
                                    <td><strong>${tx.reference}</strong></td>
                                    <td style="white-space:nowrap;">${tx.created}</td>
                                    <td>${tx.type}</td>
                                    <td>${not empty tx.fromAccountNumber ? tx.fromAccountNumber : '—'}</td>
                                    <td>${not empty tx.toAccountNumber ? tx.toAccountNumber : '—'}</td>
                                    <td class="${tx.type == 'DEPOSIT' ? 'amount-credit' : 'amount-debit'}">
                                        NPR <fmt:formatNumber value="${tx.amount}" pattern="#,##0.00"/>
                                    </td>
                                    <td><span class="badge ${tx.status == 'SUCCESS' ? 'badge-success' : tx.status == 'ROLLED_BACK' ? 'badge-warning' : 'badge-danger'}">${tx.status}</span></td>
                                    <td>${tx.remarks}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div style="text-align:right;margin-top:16px;font-size:13px;color:#5a6e8a;">
                        Generated on: <%= new java.util.Date() %>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</div>
</body>
</html>
