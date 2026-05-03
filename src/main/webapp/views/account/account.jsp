<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@0;1&family=DM+Sans:wght@400;500;600&display=swap"
          rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'DM Sans', sans-serif;
            background: #f1f5f9;
            padding: 48px 20px 80px;
            color: #0f172a;
        }

        .page {
            max-width: 620px;
            margin: 0 auto;
        }

        .topbar {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 48px;
        }

        .logo {
            display: flex;
            align-items: center;
            gap: 10px;
            text-decoration: none;
        }

        .logo-mark {
            width: 38px;
            height: 38px;
            border-radius: 10px;
            background: #1d4ed8;
            color: #fff;
            font-family: 'DM Serif Display', serif;
            font-size: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .logo-name {
            font-size: 17px;
            font-weight: 600;
            color: #0f172a;
        }

        .logo-name span {
            color: #b45309;
        }

        .back-link {
            font-size: 14px;
            font-weight: 500;
            color: #94a3b8;
            text-decoration: none;
        }

        .back-link:hover {
            color: #1d4ed8;
        }

        .hero {
            margin-bottom: 40px;
        }

        .hero-tag {
            font-size: 12px;
            font-weight: 600;
            letter-spacing: 1.5px;
            text-transform: uppercase;
            color: #1d4ed8;
            margin-bottom: 10px;
        }

        .hero h1 {
            font-family: 'DM Serif Display', serif;
            font-size: 44px;
            line-height: 1.15;
            color: #0f172a;
            margin-bottom: 8px;
        }

        .hero h1 em {
            font-style: italic;
            color: #b45309;
        }

        .hero p {
            font-size: 16px;
            color: #64748b;
        }

        .alert {
            background: #fef2f2;
            border-left: 3px solid #ef4444;
            color: #b91c1c;
            padding: 13px 16px;
            border-radius: 10px;
            font-size: 14px;
            margin-bottom: 20px;
        }

        .section {
            background: #fff;
            border: 1.5px solid #e2e8f0;
            border-radius: 14px;
            padding: 28px 30px;
            margin-bottom: 14px;
        }

        .section-title {
            font-size: 11px;
            font-weight: 600;
            letter-spacing: 1.3px;
            text-transform: uppercase;
            color: #94a3b8;
            padding-bottom: 14px;
            border-bottom: 1px solid #f1f5f9;
            margin-bottom: 20px;
        }

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

        .field {
            display: flex;
            flex-direction: column;
            gap: 6px;
        }

        label {
            font-size: 14px;
            font-weight: 500;
            color: #334155;
        }

        input, select {
            width: 100%;
            padding: 12px 14px;
            font-family: 'DM Sans', sans-serif;
            font-size: 15px;
            color: #0f172a;
            background: #f8fafc;
            border: 1.5px solid #e2e8f0;
            border-radius: 10px;
            outline: none;
            appearance: none;
            transition: border-color .15s;
        }

        input::placeholder {
            color: #cbd5e1;
        }

        input:focus, select:focus {
            border-color: #1d4ed8;
            background: #fff;
        }

        .sel {
            position: relative;
        }

        .sel::after {
            content: '';
            position: absolute;
            right: 14px;
            top: 50%;
            transform: translateY(-50%);
            border-left: 4px solid transparent;
            border-right: 4px solid transparent;
            border-top: 5px solid #94a3b8;
            pointer-events: none;
        }

        .sel select {
            padding-right: 34px;
            cursor: pointer;
        }

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
            border: 1.5px solid #e2e8f0;
            border-radius: 12px;
            padding: 20px;
            background: #f8fafc;
            transition: border-color .15s, background .15s;
        }

        .type-card input:checked + .type-card-body {
            border-color: #1d4ed8;
            background: #eff6ff;
        }

        .type-card-body:hover {
            border-color: #93c5fd;
        }

        .type-icon {
            font-size: 26px;
            margin-bottom: 10px;
        }

        .type-name {
            font-size: 16px;
            font-weight: 600;
            color: #0f172a;
            margin-bottom: 3px;
        }

        .type-desc {
            font-size: 13px;
            color: #64748b;
        }

        .submit-btn {
            width: 100%;
            margin-top: 6px;
            padding: 16px;
            background: #1d4ed8;
            color: #fff;
            font-family: 'DM Sans', sans-serif;
            font-size: 16px;
            font-weight: 600;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            transition: background .15s;
        }

        .submit-btn:hover {
            background: #1e40af;
        }

        .footer-note {
            text-align: center;
            font-size: 13px;
            color: #94a3b8;
            margin-top: 16px;
        }

        .footer-note a {
            color: #64748b;
            text-decoration: none;
        }

        .footer-note a:hover {
            color: #1d4ed8;
        }

        @media (max-width: 540px) {
            .grid-2, .grid-3, .type-row {
                grid-template-columns: 1fr;
            }

            .span-2 {
                grid-column: span 1;
            }

            .hero h1 {
                font-size: 32px;
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
        <p>Fill in your details. It only takes a few minutes.</p>
    </div>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert"><%= request.getAttribute("error") %>
    </div>
    <% } %>

    <form action="<%=request.getContextPath()%>/account" method="post">

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
