<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@0;1&family=DM+Sans:wght@300;400;500;600&display=swap"
          rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'DM Sans', sans-serif;
            background: #0c0f1a;
            min-height: 100vh;
            padding: 48px 16px 80px;
            color: #f1f5f9;
        }

        .page {
            max-width: 620px;
            margin: 0 auto;
        }

        /* Top bar */
        .topbar {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 40px;
        }

        .logo {
            display: flex;
            align-items: center;
            gap: 10px;
            text-decoration: none;
        }

        .logo-mark {
            width: 36px;
            height: 36px;
            border-radius: 9px;
            background: #2563eb;
            color: #fff;
            font-family: 'DM Serif Display', serif;
            font-size: 18px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .logo-name {
            font-size: 16px;
            font-weight: 600;
            color: #f1f5f9;
        }

        .logo-name span {
            color: #f0b429;
        }

        .back-link {
            font-size: 13px;
            color: #64748b;
            text-decoration: none;
        }

        .back-link:hover {
            color: #94a3b8;
        }

        /* Hero */
        .hero {
            margin-bottom: 36px;
        }

        .hero-tag {
            font-size: 11px;
            font-weight: 600;
            letter-spacing: 1.5px;
            text-transform: uppercase;
            color: #3b82f6;
            margin-bottom: 10px;
        }

        .hero h1 {
            font-family: 'DM Serif Display', serif;
            font-size: 34px;
            line-height: 1.2;
            color: #f1f5f9;
            margin-bottom: 8px;
        }

        .hero h1 em {
            font-style: italic;
            color: #f0b429;
        }

        .hero p {
            font-size: 14px;
            color: #64748b;
        }

        /* Error alert */
        .alert {
            background: rgba(239, 68, 68, 0.1);
            border-left: 3px solid #ef4444;
            color: #fca5a5;
            padding: 12px 16px;
            border-radius: 8px;
            font-size: 13.5px;
            margin-bottom: 20px;
        }

        /* Section */
        .section {
            background: #111827;
            border: 1px solid #1e293b;
            border-radius: 14px;
            padding: 26px 28px;
            margin-bottom: 14px;
        }

        .section-title {
            font-size: 13px;
            font-weight: 600;
            color: #475569;
            letter-spacing: .8px;
            text-transform: uppercase;
            padding-bottom: 14px;
            border-bottom: 1px solid #1e293b;
            margin-bottom: 20px;
        }

        /* Grid */
        .grid-2 {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 14px;
        }

        .grid-3 {
            display: grid;
            grid-template-columns: 1fr 1fr 1fr;
            gap: 14px;
        }

        .span-2 {
            grid-column: span 2;
        }

        /* Field */
        .field {
            display: flex;
            flex-direction: column;
            gap: 6px;
        }

        label {
            font-size: 12.5px;
            font-weight: 500;
            color: #94a3b8;
        }

        input, select {
            width: 100%;
            padding: 10px 13px;
            font-family: 'DM Sans', sans-serif;
            font-size: 14px;
            color: #f1f5f9;
            background: #0d1117;
            border: 1.5px solid #1e293b;
            border-radius: 8px;
            outline: none;
            transition: border-color .15s;
            appearance: none;
        }

        input::placeholder {
            color: #334155;
        }

        input:focus, select:focus {
            border-color: #2563eb;
        }

        /* Select arrow */
        .sel {
            position: relative;
        }

        .sel::after {
            content: '';
            position: absolute;
            right: 13px;
            top: 50%;
            transform: translateY(-50%);
            border-left: 4px solid transparent;
            border-right: 4px solid transparent;
            border-top: 5px solid #475569;
            pointer-events: none;
        }

        .sel select {
            padding-right: 34px;
            cursor: pointer;
        }

        .sel select option {
            background: #1e293b;
        }

        /* Account type cards */
        .type-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 12px;
        }

        .type-card {
            cursor: pointer;
        }

        .type-card input {
            display: none;
        }

        .type-card-body {
            border: 1.5px solid #1e293b;
            border-radius: 10px;
            padding: 16px;
            background: #0d1117;
            transition: border-color .15s;
        }

        .type-card input:checked + .type-card-body {
            border-color: #2563eb;
            background: rgba(37, 99, 235, 0.06);
        }

        .type-card-body:hover {
            border-color: #334155;
        }

        .type-icon {
            font-size: 22px;
            margin-bottom: 8px;
        }

        .type-name {
            font-size: 14px;
            font-weight: 600;
            color: #f1f5f9;
            margin-bottom: 3px;
        }

        .type-desc {
            font-size: 12px;
            color: #475569;
        }

        /* Submit */
        .submit-btn {
            width: 100%;
            margin-top: 8px;
            padding: 14px;
            background: #2563eb;
            color: #fff;
            font-family: 'DM Sans', sans-serif;
            font-size: 15px;
            font-weight: 600;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            transition: background .15s;
        }

        .submit-btn:hover {
            background: #1d4ed8;
        }

        .footer-note {
            text-align: center;
            font-size: 12px;
            color: #334155;
            margin-top: 16px;
        }

        .footer-note a {
            color: #475569;
            text-decoration: none;
        }

        .footer-note a:hover {
            color: #64748b;
        }

        @media (max-width: 560px) {
            .grid-2, .grid-3, .type-row {
                grid-template-columns: 1fr;
            }

            .span-2 {
                grid-column: span 1;
            }

            .hero h1 {
                font-size: 26px;
            }

            .section {
                padding: 20px 16px;
            }
        }
    </style>
</head>
<body>
<div class="page">

    <div class="topbar">
        <a href="#" class="logo">
            <div class="logo-mark">A</div>
            <div class="logo-name">Atomic <span>Bank</span></div>
        </a>
        <a href="<%= request.getContextPath() %>/login" class="back-link">← Back to sign in</a>
    </div>

    <div class="hero">
        <div class="hero-tag">New Account</div>
        <h1>Open your account<br>with <em>Atomic.</em></h1>
        <p>Fill in your details below. It only takes a few minutes.</p>
    </div>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert"><%= request.getAttribute("error") %>
    </div>
    <% } %>

    <form action="<%=request.getContextPath()%>/account" method="post">

        <!-- Account Type -->
        <div class="section">
            <div class="section-title">Account Type</div>
            <div class="type-row">
                <label class="type-card">
                    <input type="radio" name="accountType" value="SAVINGS" required>
                    <div class="type-card-body">
                        <div class="type-icon">💰</div>
                        <div class="type-name">Savings</div>
                        <div class="type-desc">Earn interest on your balance</div>
                    </div>
                </label>
                <label class="type-card">
                    <input type="radio" name="accountType" value="CURRENT">
                    <div class="type-card-body">
                        <div class="type-icon">🏦</div>
                        <div class="type-name">Current</div>
                        <div class="type-desc">For everyday business use</div>
                    </div>
                </label>
            </div>
        </div>

        <!-- KYC -->
        <div class="section">
            <div class="section-title">KYC Details</div>
            <div class="grid-2">
                <div class="field">
                    <label>Date of Birth</label>
                    <input type="date" name="dob" required>
                </div>
                <div class="field">
                    <label>Gender</label>
                    <div class="sel">
                        <select name="gender" required>
                            <option value="">Select gender</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <label>Citizenship No.</label>
                    <input type="text" name="citizenship" placeholder="12-34-56-78901" required>
                </div>
                <div class="field">
                    <label>Issue Date</label>
                    <input type="date" name="citizenshipIssueDate" required>
                </div>
                <div class="field">
                    <label>Issue District</label>
                    <input type="text" name="citizenshipDistrict" placeholder="e.g. Kathmandu" required>
                </div>
                <div class="field">
                    <label>Phone</label>
                    <input type="text" name="phone" placeholder="+977 98XXXXXXXX" required>
                </div>
                <div class="field">
                    <label>Occupation</label>
                    <input type="text" name="occupation" placeholder="e.g. Engineer">
                </div>
                <div class="field">
                    <label>Annual Income (NPR)</label>
                    <input type="number" name="income" placeholder="0.00" step="0.01" min="0">
                </div>
            </div>
        </div>

        <!-- Address -->
        <div class="section">
            <div class="section-title">Address</div>
            <div class="grid-3">
                <div class="field">
                    <label>Province</label>
                    <input type="text" name="province" placeholder="Province">
                </div>
                <div class="field">
                    <label>District</label>
                    <input type="text" name="district" placeholder="District">
                </div>
                <div class="field">
                    <label>City</label>
                    <input type="text" name="city" placeholder="City">
                </div>
                <div class="field">
                    <label>Ward No.</label>
                    <input type="number" name="ward" placeholder="1–32" min="1" max="32">
                </div>
                <div class="field span-2">
                    <label>Tole / Street</label>
                    <input type="text" name="tole" placeholder="Street or tole name">
                </div>
            </div>
        </div>

        <!-- Family -->
        <div class="section">
            <div class="section-title">Family Details</div>
            <div class="grid-2">
                <div class="field">
                    <label>Father's Name</label>
                    <input type="text" name="fatherName" placeholder="Full name">
                </div>
                <div class="field">
                    <label>Mother's Name</label>
                    <input type="text" name="motherName" placeholder="Full name">
                </div>
            </div>
        </div>

        <button type="submit" class="submit-btn">Open My Account</button>

        <p class="footer-note">
            By continuing you agree to <a href="#">Terms</a> and <a href="#">Privacy Policy</a>. &copy; 2025 Atomic
            Bank.
        </p>

    </form>
</div>
</body>
</html>
