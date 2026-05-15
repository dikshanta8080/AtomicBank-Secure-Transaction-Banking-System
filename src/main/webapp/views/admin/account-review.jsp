<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Review Account — Atomic Bank Admin</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/account-review.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/admin-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>KYC Review</h2>
            </div>
            <div class="topbar-actions">
                <a class="btn btn-ghost" href="${pageContext.request.contextPath}/admin/accounts">← Back to Accounts</a>
            </div>
        </div>

        <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

        <c:choose>
            <c:when test="${detail == null}">
                <div class="card"><div class="empty-state"><p>Account details not found.</p></div></div>
            </c:when>
            <c:otherwise>
                <div class="review-layout">
                    <div class="card">
                        <div class="review-section-title">Personal Information</div>
                        <div class="info-list">
                            <div class="info-row">
                                <span class="info-key kyc-label">Full Name</span>
                                <span class="info-val kyc-detail-value">${detail.name}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">Email</span>
                                <span class="info-val kyc-detail-value">${detail.email}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">Phone</span>
                                <span class="info-val kyc-detail-value">${detail.phone}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">Date of Birth</span>
                                <span class="info-val kyc-detail-value">${detail.dob}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">Gender</span>
                                <span class="info-val kyc-detail-value">${detail.gender}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">Occupation</span>
                                <span class="info-val kyc-detail-value">${detail.occupation}</span>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="review-section-title">KYC Details</div>
                        <div class="info-list">
                            <div class="info-row">
                                <span class="info-key kyc-label">Citizenship No.</span>
                                <span class="info-val kyc-detail-value">${detail.citizenship}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">KYC Status</span>
                                <span class="info-val">
                                    <span class="badge badge-warning">${detail.kycStatus}</span>
                                </span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">Account Type</span>
                                <span class="info-val kyc-detail-value">${detail.accountType}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">Account Status</span>
                                <span class="info-val">
                                    <span class="badge badge-warning">${detail.accountStatus}</span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="review-layout" style="margin-top:20px;">
                    <div class="card">
                        <div class="review-section-title">Address Details</div>
                        <div class="info-list">
                            <div class="info-row">
                                <span class="info-key kyc-label">Province</span>
                                <span class="info-val kyc-detail-value">${detail.province}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">District</span>
                                <span class="info-val kyc-detail-value">${detail.district}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">City</span>
                                <span class="info-val kyc-detail-value">${detail.city}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">Ward No.</span>
                                <span class="info-val kyc-detail-value">${detail.ward}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">Tole</span>
                                <span class="info-val kyc-detail-value">${detail.tole}</span>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="review-section-title">Family Details</div>
                        <div class="info-list">
                            <div class="info-row">
                                <span class="info-key kyc-label">Father's Name</span>
                                <span class="info-val kyc-detail-value">${detail.fatherName}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-key kyc-label">Mother's Name</span>
                                <span class="info-val kyc-detail-value">${detail.motherName}</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card review-decision-card">
                    <div class="review-section-title">Decision</div>
                    <div class="decision-actions">
                        <form action="${pageContext.request.contextPath}/admin/accounts" method="post">
                            <input type="hidden" name="userId" value="${detail.userId}">
                            <input type="hidden" name="action" value="approve">
                            <button class="btn btn-success" type="submit">✓ Approve Account</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/accounts" method="post">
                            <input type="hidden" name="userId" value="${detail.userId}">
                            <input type="hidden" name="action" value="reject">
                            <button class="btn btn-danger" type="submit">✕ Reject &amp; Remove</button>
                        </form>
                        <a class="btn btn-ghost" href="${pageContext.request.contextPath}/admin/accounts">Cancel</a>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
</div>
</body>
</html>
