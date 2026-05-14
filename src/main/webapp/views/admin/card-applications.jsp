<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Card Applications — Atomic Bank Admin</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/card-applications.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/admin-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>Card Applications</h2>
                <p>Review pending credit card applications. Set the limit and expiry date before issuing.</p>
            </div>
        </div>

        <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

        <div class="card">
            <c:choose>
                <c:when test="${empty pendingCards}">
                    <div class="empty-state">
                        <p>No pending card applications at this time.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrap">
                        <table>
                            <thead>
                            <tr>
                                <th>Card ID</th>
                                <th>Applicant</th>
                                <th>Account No.</th>
                                <th>Type</th>
                                <th>Requested Limit</th>
                                <th>Monthly Income</th>
                                <th>Applied</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pendingCards}" var="card">
                                <tr>
                                    <td><strong>#${card.id}</strong></td>
                                    <td>${card.userName}</td>
                                    <td><code>${card.accountNumber}</code></td>
                                    <td>${card.type}</td>
                                    <td>NPR <fmt:formatNumber value="${card.creditLimit}" pattern="#,##0.00"/></td>
                                    <td>NPR <fmt:formatNumber value="${card.monthlyIncome}" pattern="#,##0.00"/></td>
                                    <td>${card.appliedDate}</td>
                                    <td>
                                        <div style="display:flex;gap:8px;flex-wrap:wrap;align-items:flex-start;">
                                            <form action="${pageContext.request.contextPath}/admin/cards" method="post" style="display:grid;gap:6px;">
                                                <input type="hidden" name="cardId" value="${card.id}">
                                                <input type="hidden" name="action" value="issue">
                                                <div style="display:flex;gap:6px;align-items:center;">
                                                    <input type="number" name="creditLimit" value="${card.creditLimit}"
                                                           min="1" step="0.01" style="width:120px;padding:7px 10px;border:1.5px solid #d6e3f5;border-radius:8px;font:inherit;font-size:13px;"
                                                           placeholder="Credit limit" required>
                                                    <input type="date" name="expiryDate"
                                                           style="width:140px;padding:7px 10px;border:1.5px solid #d6e3f5;border-radius:8px;font:inherit;font-size:13px;"
                                                           required>
                                                    <button class="btn btn-success btn-sm" type="submit">Issue Card</button>
                                                </div>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/admin/cards" method="post">
                                                <input type="hidden" name="cardId" value="${card.id}">
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
    </main>
</div>
</body>
</html>
