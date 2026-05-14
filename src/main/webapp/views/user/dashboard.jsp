<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-dashboard.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/user-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>Welcome back, ${sessionScope.user.name}</h2>
                <p>Here's an overview of your account activity.</p>
            </div>
            <div class="topbar-actions">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/deposit">Deposit</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/transfer">Transfer</a>
            </div>
        </div>

        <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

        <c:choose>
            <c:when test="${account == null}">
                <div class="card" style="text-align:center;padding:48px;">
                    <div style="font-size:48px;margin-bottom:16px;">🏦</div>
                    <h3 style="margin-bottom:10px;">No Account Yet</h3>
                    <p style="color:#5a6e8a;margin-bottom:20px;">Set up your bank account to start transacting.</p>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/account">Open an Account</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="layout-grid two" style="margin-bottom:20px;">
                    <div class="card hero-card">
                        <div class="hero-label">Available Balance</div>
                        <div class="hero-value">NPR <fmt:formatNumber value="${account.balance}" pattern="#,##0.00"/></div>
                        <div class="hero-meta">
                            <span>${account.accountNumber}</span>
                            <span>${account.type}</span>
                            <span>${account.status}</span>
                        </div>
                    </div>
                    <div class="card">
                        <div class="section-header"><h3>Quick Actions</h3></div>
                        <div class="actions">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/deposit">Deposit</a>
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/withdraw">Withdraw</a>
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/transfer">Transfer</a>
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/statement">Statement</a>
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/cards/apply">Apply Card</a>
                            <a class="btn btn-ghost" href="${pageContext.request.contextPath}/interest-summary">Interest</a>
                        </div>
                    </div>
                </div>

                <div class="stats">
                    <div class="stat">
                        <div class="stat-label">Total Transactions</div>
                        <div class="stat-value">${transactionCount}</div>
                        <div class="stat-sub">All recorded operations</div>
                    </div>
                    <div class="stat">
                        <div class="stat-label">Interest Earned</div>
                        <div class="stat-value">NPR <fmt:formatNumber value="${interestAmount}" pattern="#,##0.00"/></div>
                        <div class="stat-sub">Current savings estimate</div>
                    </div>
                    <div class="stat">
                        <div class="stat-label">Issued Cards</div>
                        <div class="stat-value">${issuedCards}</div>
                        <div class="stat-sub">Active issued cards</div>
                    </div>
                    <div class="stat">
                        <div class="stat-label">Pending Cards</div>
                        <div class="stat-value">${pendingCards}</div>
                        <div class="stat-sub">Awaiting admin review</div>
                    </div>
                </div>

                <div class="card" style="margin-top:20px;">
                    <div class="section-header">
                        <h3>Recent Transactions</h3>
                        <a class="btn btn-ghost btn-sm" href="${pageContext.request.contextPath}/transactions">View all</a>
                    </div>
                    <c:choose>
                        <c:when test="${empty transactions}">
                            <div class="empty-state">
                                <p>No transactions yet. Start by making a deposit.</p>
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
                                        <th>Date</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${transactions}" var="tx">
                                        <tr>
                                            <td><strong>${tx.reference}</strong></td>
                                            <td>${tx.type}</td>
                                            <td>${not empty tx.fromAccountNumber ? tx.fromAccountNumber : '—'}</td>
                                            <td>${not empty tx.toAccountNumber ? tx.toAccountNumber : '—'}</td>
                                            <td class="${tx.type == 'DEPOSIT' ? 'amount-credit' : 'amount-debit'}">NPR <fmt:formatNumber value="${tx.amount}" pattern="#,##0.00"/></td>
                                            <td>
                                                <span class="badge ${tx.status == 'SUCCESS' ? 'badge-success' : tx.status == 'ROLLED_BACK' ? 'badge-warning' : 'badge-danger'}">${tx.status}</span>
                                            </td>
                                            <td>${tx.created}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
</div>
</body>
</html>
