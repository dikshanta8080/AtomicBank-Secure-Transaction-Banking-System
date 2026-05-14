<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Open Account — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/create-account.css">
</head>
<body>
<div class="app-shell">
    <%@ include file="/views/common/user-nav.jspf" %>
    <main class="workspace">
        <div class="topbar">
            <div>
                <h2>Open a Bank Account</h2>
                <p>Fill in your KYC details to submit an account creation request.</p>
            </div>
        </div>

        <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

        <div class="card">
            <form action="${pageContext.request.contextPath}/account" method="post">

                <div class="form-section-title">Account Information</div>
                <div class="form-grid two">
                    <div class="field">
                        <label for="accountType">Account Type</label>
                        <select id="accountType" name="accountType" required>
                            <option value="" disabled selected>Select type...</option>
                            <option value="SAVINGS">Savings Account</option>
                            <option value="CURRENT">Current Account</option>
                        </select>
                    </div>
                    <div class="field">
                        <label for="dob">Date of Birth</label>
                        <input type="date" id="dob" name="dob" required>
                    </div>
                </div>

                <div class="form-grid two">
                    <div class="field">
                        <label for="gender">Gender</label>
                        <select id="gender" name="gender" required>
                            <option value="" disabled selected>Select gender...</option>
                            <option value="MALE">Male</option>
                            <option value="FEMALE">Female</option>
                            <option value="OTHER">Other</option>
                        </select>
                    </div>
                    <div class="field">
                        <label for="phone">Phone Number</label>
                        <input type="text" id="phone" name="phone" placeholder="98XXXXXXXX" required>
                    </div>
                </div>

                <hr class="divider">
                <div class="form-section-title">KYC — Citizenship Details</div>
                <div class="form-grid three">
                    <div class="field">
                        <label for="citizenship">Citizenship Number</label>
                        <input type="text" id="citizenship" name="citizenship" placeholder="XX-XX-XXXXXXX" required>
                    </div>
                    <div class="field">
                        <label for="citizenshipIssueDate">Issue Date</label>
                        <input type="date" id="citizenshipIssueDate" name="citizenshipIssueDate" required>
                    </div>
                    <div class="field">
                        <label for="citizenshipDistrict">Issue District</label>
                        <input type="text" id="citizenshipDistrict" name="citizenshipDistrict" placeholder="e.g. Kathmandu" required>
                    </div>
                </div>

                <div class="form-grid two">
                    <div class="field">
                        <label for="occupation">Occupation</label>
                        <input type="text" id="occupation" name="occupation" placeholder="e.g. Software Engineer" required>
                    </div>
                    <div class="field">
                        <label for="income">Annual Income (NPR)</label>
                        <input type="number" id="income" name="income" placeholder="e.g. 600000" min="1" required>
                    </div>
                </div>

                <hr class="divider">
                <div class="form-section-title">Address Details</div>
                <div class="form-grid three">
                    <div class="field">
                        <label for="province">Province</label>
                        <input type="text" id="province" name="province" placeholder="e.g. Bagmati" required>
                    </div>
                    <div class="field">
                        <label for="district">District</label>
                        <input type="text" id="district" name="district" placeholder="e.g. Kathmandu" required>
                    </div>
                    <div class="field">
                        <label for="city">City / Municipality</label>
                        <input type="text" id="city" name="city" placeholder="e.g. Kathmandu Metropolitan" required>
                    </div>
                </div>
                <div class="form-grid two">
                    <div class="field">
                        <label for="ward">Ward Number</label>
                        <input type="number" id="ward" name="ward" placeholder="e.g. 5" min="1" required>
                    </div>
                    <div class="field">
                        <label for="tole">Tole / Street</label>
                        <input type="text" id="tole" name="tole" placeholder="e.g. Baneshwor" required>
                    </div>
                </div>

                <hr class="divider">
                <div class="form-section-title">Family Details</div>
                <div class="form-grid two">
                    <div class="field">
                        <label for="fatherName">Father's Name</label>
                        <input type="text" id="fatherName" name="fatherName" placeholder="Full name" required>
                    </div>
                    <div class="field">
                        <label for="motherName">Mother's Name</label>
                        <input type="text" id="motherName" name="motherName" placeholder="Full name" required>
                    </div>
                </div>

                <div style="display:flex;gap:12px;justify-content:flex-end;margin-top:8px;">
                    <a class="btn btn-ghost" href="${pageContext.request.contextPath}/UserDashboard">Cancel</a>
                    <button type="submit" class="btn btn-primary">Submit Account Request</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
