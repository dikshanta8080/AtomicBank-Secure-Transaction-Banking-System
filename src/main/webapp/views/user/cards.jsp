<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Cards — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cards.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/user-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>My Cards</h2>
            </div>
            <div class="topbar-actions">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/cards/apply">Apply for New Card</a>
            </div>
        </div>

        <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

        <c:choose>
            <c:when test="${empty cards}">
                <div class="card no-cards-state">
                    <div class="no-cards-icon">💳</div>
                    <h3>No Cards Yet</h3>
                    <p>No cards on file.</p>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/cards/apply">Apply for a Card</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="cards-grid">
                    <c:forEach items="${cards}" var="card">
                        <div>
                            <div class="credit-card-widget ${card.status == 'REJECTED' ? 'rejected' : ''}">
                                <span class="card-type-badge">${card.type}</span>
                                <div class="card-chip"></div>
                                <div class="card-number-display">
                                    <c:choose>
                                        <c:when test="${not empty card.cardNumber and card.cardNumber.length() >= 16}">
                                            ${card.cardNumber.substring(0,4)} ****
                                            **** ${card.cardNumber.substring(card.cardNumber.length()-4)}
                                        </c:when>
                                        <c:otherwise>
                                            **** **** **** ****
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="card-bottom-row">
                                    <div class="card-holder-section">
                                        <div class="card-label">Card Holder</div>
                                        <div class="card-value">${sessionScope.user.name}</div>
                                    </div>
                                    <div class="card-expiry-section">
                                        <div class="card-label">Expires</div>
                                        <div class="card-value">${card.expiryDate}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-details-panel">
                                <div class="info-list">
                                    <div class="info-row">
                                        <span class="info-key">Card Type</span>
                                        <span class="info-val">${card.type}</span>
                                    </div>
                                    <div class="info-row">
                                        <span class="info-key">Status</span>
                                        <span class="info-val">
                                            <span class="badge ${card.status == 'ISSUED' ? 'badge-success' : card.status == 'PENDING' ? 'badge-warning' : 'badge-danger'}">${card.status}</span>
                                        </span>
                                    </div>
                                    <c:if test="${card.creditLimit != null and card.creditLimit > 0}">
                                        <div class="info-row">
                                            <span class="info-key">Credit Limit</span>
                                            <span class="info-val">NPR <fmt:formatNumber value="${card.creditLimit}" pattern="#,##0.00"/></span>
                                        </div>
                                    </c:if>
                                    <c:if test="${card.expiryDate != null}">
                                        <div class="info-row">
                                            <span class="info-key">Expiry Date</span>
                                            <span class="info-val">${card.expiryDate}</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty card.rejectionReason}">
                                        <div class="info-row">
                                            <span class="info-key">Rejection Reason</span>
                                            <span class="info-val" style="color:#dc2626;">${card.rejectionReason}</span>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
</div>
</body>
</html>
