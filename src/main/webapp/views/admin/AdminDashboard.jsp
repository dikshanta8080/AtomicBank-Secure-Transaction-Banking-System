<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.banking.sathi.model.User, com.banking.sathi.enums.Role" %>
<%
    User loggedInUser = (User) session.getAttribute("user");
    if (loggedInUser == null || loggedInUser.getRole() != Role.ADMIN) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    String adminName = loggedInUser.getName();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AtomicBank — Admin Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Syne:wght@600;700;800&family=DM+Sans:opsz,wght@9..40,400;9..40,500;9..40,600&display=swap"
          rel="stylesheet">
    <style>
        /* ===== RESET & BASE ===== */
        *, *::before, *::after {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'DM Sans', sans-serif;
            background: #f1f5f9;
            min-height: 100vh;
        }

        :root {
            --navy: #0a1628;
            --navy2: #0f2340;
            --blue: #2563eb;
            --blue-mid: #1d4ed8;
            --blue-light: #dbeafe;
            --surface: #f8fafc;
            --card: #fff;
            --border: #e2e8f0;
            --text: #0f172a;
            --text2: #475569;
            --text3: #94a3b8;
            --green: #10b981;
            --green-bg: #d1fae5;
            --red: #ef4444;
            --red-bg: #fee2e2;
            --amber: #f59e0b;
            --amber-bg: #fef3c7;
        }

        /* ===== LAYOUT ===== */
        .app {
            display: flex;
            height: 100vh;
            overflow: hidden;
        }

        /* ===== SIDEBAR ===== */
        .sidebar {
            width: 240px;
            background: var(--navy);
            height: 100%;
            display: flex;
            flex-direction: column;
            flex-shrink: 0;
        }

        .sidebar-logo {
            padding: 20px 20px 16px;
            display: flex;
            align-items: center;
            gap: 10px;
            border-bottom: 1px solid rgba(255, 255, 255, .08);
            text-decoration: none;
        }

        .logo-mark {
            width: 34px;
            height: 34px;
            background: var(--blue);
            border-radius: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Syne', sans-serif;
            font-size: 18px;
            font-weight: 800;
            color: #fff;
            flex-shrink: 0;
        }

        .logo-text {
            font-family: 'Syne', sans-serif;
            font-size: 17px;
            font-weight: 700;
            color: #fff;
        }

        .sidebar-section {
            padding: 16px 20px 6px;
            font-size: 10px;
            font-weight: 600;
            color: rgba(255, 255, 255, .3);
            letter-spacing: 1.2px;
            text-transform: uppercase;
        }

        .nav-link {
            display: flex;
            align-items: center;
            gap: 9px;
            padding: 9px 12px;
            border-radius: 9px;
            margin: 1px 8px;
            cursor: pointer;
            color: rgba(255, 255, 255, .55);
            font-size: 13.5px;
            font-weight: 500;
            text-decoration: none;
            transition: background .15s, color .15s;
        }

        .nav-link:hover {
            background: rgba(255, 255, 255, .07);
            color: #fff;
        }

        .nav-link.active {
            background: var(--blue);
            color: #fff;
        }

        .nav-icon {
            font-size: 16px;
            width: 20px;
            text-align: center;
            flex-shrink: 0;
        }

        .pending-badge {
            background: var(--red);
            color: #fff;
            border-radius: 20px;
            font-size: 10px;
            font-weight: 700;
            padding: 1px 6px;
            margin-left: auto;
        }

        .sidebar-bottom {
            margin-top: auto;
            padding: 14px 16px;
            border-top: 1px solid rgba(255, 255, 255, .08);
        }

        .user-badge {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .user-avatar {
            width: 34px;
            height: 34px;
            border-radius: 50%;
            background: var(--blue);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 13px;
            font-weight: 700;
            color: #fff;
            flex-shrink: 0;
        }

        .user-name {
            font-size: 13px;
            font-weight: 600;
            color: #fff;
        }

        .user-role {
            font-size: 11px;
            color: rgba(255, 255, 255, .38);
        }

        /* ===== MAIN AREA ===== */
        .main {
            flex: 1;
            display: flex;
            flex-direction: column;
            overflow: hidden;
        }

        /* ===== TOPBAR ===== */
        .topbar {
            height: 60px;
            background: #fff;
            border-bottom: 1px solid var(--border);
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 24px;
            flex-shrink: 0;
        }

        .topbar-title {
            font-family: 'Syne', sans-serif;
            font-size: 18px;
            font-weight: 700;
            color: var(--text);
        }

        .topbar-actions {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .icon-btn {
            width: 34px;
            height: 34px;
            border-radius: 9px;
            background: var(--surface);
            border: 1px solid var(--border);
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            font-size: 15px;
            color: var(--text2);
            position: relative;
            text-decoration: none;
        }

        .notif-dot {
            position: absolute;
            top: 5px;
            right: 5px;
            width: 7px;
            height: 7px;
            background: var(--red);
            border-radius: 50%;
            border: 1.5px solid #fff;
        }

        .topbar-avatar {
            width: 34px;
            height: 34px;
            border-radius: 50%;
            background: var(--blue);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 13px;
            font-weight: 700;
            color: #fff;
            cursor: pointer;
        }

        /* ===== PAGE BODY ===== */
        .page-body {
            flex: 1;
            overflow-y: auto;
            padding: 24px;
        }

        .page-body::-webkit-scrollbar {
            width: 5px;
        }

        .page-body::-webkit-scrollbar-thumb {
            background: #cbd5e1;
            border-radius: 3px;
        }

        /* ===== CARDS ===== */
        .card {
            background: var(--card);
            border-radius: 16px;
            border: 1px solid var(--border);
            padding: 20px;
        }

        .card + .card {
            margin-top: 16px;
        }

        /* ===== STATS GRID ===== */
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 14px;
            margin-bottom: 22px;
        }

        .stat-card {
            background: var(--card);
            border-radius: 14px;
            padding: 18px;
            border: 1px solid var(--border);
        }

        .stat-icon {
            font-size: 22px;
            margin-bottom: 10px;
        }

        .stat-label {
            font-size: 11px;
            font-weight: 600;
            color: var(--text3);
            text-transform: uppercase;
            letter-spacing: .5px;
            margin-bottom: 6px;
        }

        .stat-value {
            font-family: 'Syne', sans-serif;
            font-size: 22px;
            font-weight: 700;
            color: var(--text);
        }

        .stat-sub {
            font-size: 12px;
            color: var(--text3);
            margin-top: 3px;
        }

        /* ===== SECTION HEADER ===== */
        .section-title {
            font-family: 'Syne', sans-serif;
            font-size: 15px;
            font-weight: 700;
            color: var(--text);
            margin-bottom: 14px;
        }

        .section-hdr {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 14px;
        }

        /* ===== GRID LAYOUTS ===== */
        .grid-2 {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 18px;
        }

        /* ===== TABLE ===== */
        .table-wrap {
            overflow-x: auto;
            border-radius: 12px;
            border: 1px solid var(--border);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            font-size: 11px;
            font-weight: 600;
            color: var(--text3);
            text-transform: uppercase;
            letter-spacing: .5px;
            padding: 10px 14px;
            text-align: left;
            background: var(--surface);
            border-bottom: 1px solid var(--border);
        }

        td {
            padding: 12px 14px;
            font-size: 13.5px;
            color: var(--text);
            border-bottom: 1px solid #f1f5f9;
        }

        tr:last-child td {
            border-bottom: none;
        }

        tr:hover td {
            background: #f8fafc;
        }

        /* ===== PILLS ===== */
        .pill {
            display: inline-flex;
            align-items: center;
            padding: 3px 9px;
            border-radius: 20px;
            font-size: 11px;
            font-weight: 600;
        }

        .pill-green {
            background: var(--green-bg);
            color: #059669;
        }

        .pill-red {
            background: var(--red-bg);
            color: #dc2626;
        }

        .pill-amber {
            background: var(--amber-bg);
            color: #d97706;
        }

        .pill-blue {
            background: var(--blue-light);
            color: var(--blue);
        }

        .pill-gray {
            background: #f1f5f9;
            color: var(--text2);
        }

        /* ===== BUTTONS ===== */
        .btn {
            border: none;
            border-radius: 9px;
            padding: 10px 20px;
            font-size: 13.5px;
            font-weight: 600;
            cursor: pointer;
            font-family: 'DM Sans', sans-serif;
            transition: background .15s, color .15s;
            display: inline-flex;
            align-items: center;
            gap: 7px;
            text-decoration: none;
        }

        .btn-primary {
            background: var(--blue);
            color: #fff;
        }

        .btn-primary:hover {
            background: var(--blue-mid);
        }

        .btn-outline {
            background: transparent;
            color: var(--blue);
            border: 1.5px solid var(--blue);
        }

        .btn-outline:hover {
            background: var(--blue-light);
        }

        .btn-danger {
            background: var(--red-bg);
            color: #dc2626;
        }

        .btn-danger:hover {
            background: #fecaca;
        }

        .btn-success {
            background: var(--green-bg);
            color: #059669;
        }

        .btn-success:hover {
            background: #a7f3d0;
        }

        .btn-gray {
            background: var(--surface);
            color: var(--text2);
            border: 1px solid var(--border);
        }

        .btn-sm {
            padding: 6px 13px;
            font-size: 12px;
            border-radius: 7px;
        }

        .btn-lg {
            padding: 13px 26px;
            font-size: 15px;
            border-radius: 11px;
        }

        /* ===== ALERT ===== */
        .alert {
            padding: 12px 15px;
            border-radius: 11px;
            font-size: 13px;
            display: flex;
            align-items: center;
            gap: 9px;
            margin-bottom: 14px;
        }

        .alert-warning {
            background: var(--amber-bg);
            color: #d97706;
        }

        .alert-info {
            background: var(--blue-light);
            color: var(--blue);
        }

        /* ===== LOGOUT ===== */
        .logout-link {
            display: flex;
            align-items: center;
            gap: 7px;
            padding: 8px 12px;
            border-radius: 9px;
            color: rgba(255, 255, 255, .45);
            font-size: 13px;
            font-weight: 500;
            text-decoration: none;
            margin: 0 8px 8px;
            transition: background .15s, color .15s;
        }

        .logout-link:hover {
            background: rgba(239, 68, 68, .15);
            color: #fca5a5;
        }
    </style>
</head>
<body>

<div class="app">

    <!-- ============================================================ SIDEBAR -->
    <aside class="sidebar">
        <a href="<%= request.getContextPath() %>/adminDashboard" class="sidebar-logo">
            <div class="logo-mark">⚛</div>
            <div class="logo-text">AtomicBank</div>
        </a>

        <div class="sidebar-section">Admin Panel</div>

        <a href="<%= request.getContextPath() %>/adminDashboard"
           class="nav-link active" id="nav-home">
            <span class="nav-icon">🏠</span> Dashboard
        </a>

        <a href="<%= request.getContextPath() %>/manageAccounts"
           class="nav-link" id="nav-accounts">
            <span class="nav-icon">👥</span> Manage Accounts
        </a>

        <a href="<%= request.getContextPath() %>/manageAccounts?filter=pending"
           class="nav-link" id="nav-pending-accounts">
            <span class="nav-icon">🕐</span> Pending Approvals
            <span class="pending-badge">2</span>
        </a>

        <a href="<%= request.getContextPath() %>/cardApplications"
           class="nav-link" id="nav-card-apps">
            <span class="nav-icon">🎫</span> Card Applications
            <span class="pending-badge">4</span>
        </a>

        <a href="<%= request.getContextPath() %>/issueCard"
           class="nav-link" id="nav-issue-card">
            <span class="nav-icon">💳</span> Issue Credit Card
        </a>

        <div class="sidebar-section">Reports</div>

        <a href="<%= request.getContextPath() %>/allTransactions"
           class="nav-link" id="nav-all-txns">
            <span class="nav-icon">📊</span> All Transactions
        </a>

        <a href="<%= request.getContextPath() %>/failedOperations"
           class="nav-link" id="nav-failed-ops">
            <span class="nav-icon">⚠️</span> Failed Operations
            <span class="pending-badge">2</span>
        </a>

        <div style="flex:1;"></div>

        <a href="<%= request.getContextPath() %>/logout" class="logout-link">
            <span>🚪</span> Sign Out
        </a>

        <div class="sidebar-bottom">
            <div class="user-badge">
                <div class="user-avatar">AD</div>
                <div>
                    <div class="user-name"><%= adminName %>
                    </div>
                    <div class="user-role">System Administrator</div>
                </div>
            </div>
        </div>
    </aside>

    <!-- ============================================================ MAIN -->
    <div class="main">

        <!-- TOPBAR -->
        <div class="topbar">
            <div class="topbar-title">Admin Dashboard</div>
            <div class="topbar-actions">
                <a href="<%= request.getContextPath() %>/failedOperations" class="icon-btn" title="Alerts">
                    🔔
                    <span class="notif-dot"></span>
                </a>
                <a href="<%= request.getContextPath() %>/adminProfile" class="icon-btn" title="Settings">⚙️</a>
                <div class="topbar-avatar" title="<%= adminName %>">AD</div>
            </div>
        </div>

        <!-- PAGE BODY -->
        <div class="page-body">

            <!-- STAT CARDS -->
            <div class="stats-grid">
                <div class="stat-card">
                    <div class="stat-icon">👥</div>
                    <div class="stat-label">Total Accounts</div>
                    <div class="stat-value">248</div>
                    <div class="stat-sub">+12 this month</div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon">💰</div>
                    <div class="stat-label">Total Deposits</div>
                    <div class="stat-value">NPR 4.2Cr</div>
                    <div class="stat-sub">System-wide</div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon">🎫</div>
                    <div class="stat-label">Pending Card Apps</div>
                    <div class="stat-value" style="color:var(--amber);">4</div>
                    <div class="stat-sub">Awaiting review</div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon">⚠️</div>
                    <div class="stat-label">Failed Transactions</div>
                    <div class="stat-value" style="color:var(--red);">2</div>
                    <div class="stat-sub">Rolled back today</div>
                </div>
            </div>

            <!-- TWO COLUMN CONTENT -->
            <div class="grid-2">

                <!-- Recent Account Registrations -->
                <div>
                    <div class="section-hdr">
                        <div class="section-title">Recent Account Registrations</div>
                        <a href="<%= request.getContextPath() %>/manageAccounts" class="btn btn-outline btn-sm">View
                            All</a>
                    </div>
                    <div class="table-wrap">
                        <table>
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Account</th>
                                <th>Date</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <div>Priya Sharma</div>
                                    <div style="font-size:11px;color:var(--text3);">priya@email.com</div>
                                </td>
                                <td>ACC-0248</td>
                                <td>02 May 2026</td>
                                <td><span class="pill pill-amber">Pending</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <div>Raj Kumar</div>
                                    <div style="font-size:11px;color:var(--text3);">raj@email.com</div>
                                </td>
                                <td>ACC-0247</td>
                                <td>01 May 2026</td>
                                <td><span class="pill pill-green">Active</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <div>Mira Rai</div>
                                    <div style="font-size:11px;color:var(--text3);">mira@email.com</div>
                                </td>
                                <td>ACC-0246</td>
                                <td>30 Apr 2026</td>
                                <td><span class="pill pill-green">Active</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <div>Arun Joshi</div>
                                    <div style="font-size:11px;color:var(--text3);">arun@email.com</div>
                                </td>
                                <td>ACC-0245</td>
                                <td>28 Apr 2026</td>
                                <td><span class="pill pill-red">Rejected</span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Pending Card Applications -->
                <div>
                    <div class="section-hdr">
                        <div class="section-title">Pending Card Applications</div>
                        <a href="<%= request.getContextPath() %>/cardApplications" class="btn btn-outline btn-sm">Review
                            All</a>
                    </div>
                    <div style="display:flex;flex-direction:column;gap:10px;">

                        <div class="card" style="padding:14px;">
                            <div style="display:flex;align-items:center;justify-content:space-between;gap:10px;">
                                <div>
                                    <div style="font-size:13px;font-weight:600;">John Doe · Visa Classic</div>
                                    <div style="font-size:12px;color:var(--text3);">Limit: NPR 1,00,000 · 2 days
                                        pending
                                    </div>
                                </div>
                                <div style="display:flex;gap:6px;flex-shrink:0;">
                                    <a href="<%= request.getContextPath() %>/issueCard?appId=CA-2026-0042"
                                       class="btn btn-success btn-sm">✓ Approve</a>
                                    <a href="<%= request.getContextPath() %>/rejectCard?appId=CA-2026-0042"
                                       class="btn btn-danger btn-sm">✕</a>
                                </div>
                            </div>
                        </div>

                        <div class="card" style="padding:14px;">
                            <div style="display:flex;align-items:center;justify-content:space-between;gap:10px;">
                                <div>
                                    <div style="font-size:13px;font-weight:600;">Priya Sharma · Visa Gold</div>
                                    <div style="font-size:12px;color:var(--text3);">Limit: NPR 3,00,000 · 1 day
                                        pending
                                    </div>
                                </div>
                                <div style="display:flex;gap:6px;flex-shrink:0;">
                                    <a href="<%= request.getContextPath() %>/issueCard?appId=CA-2026-0248"
                                       class="btn btn-success btn-sm">✓ Approve</a>
                                    <a href="<%= request.getContextPath() %>/rejectCard?appId=CA-2026-0248"
                                       class="btn btn-danger btn-sm">✕</a>
                                </div>
                            </div>
                        </div>

                        <div class="card" style="padding:14px;">
                            <div style="display:flex;align-items:center;justify-content:space-between;gap:10px;">
                                <div>
                                    <div style="font-size:13px;font-weight:600;">Raj Kumar · Mastercard</div>
                                    <div style="font-size:12px;color:var(--text3);">Limit: NPR 5,00,000 · 3 days
                                        pending
                                    </div>
                                </div>
                                <div style="display:flex;gap:6px;flex-shrink:0;">
                                    <a href="<%= request.getContextPath() %>/issueCard?appId=CA-2026-0247"
                                       class="btn btn-success btn-sm">✓ Approve</a>
                                    <a href="<%= request.getContextPath() %>/rejectCard?appId=CA-2026-0247"
                                       class="btn btn-danger btn-sm">✕</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div><!-- /grid-2 -->

            <!-- ====== PENDING ACCOUNT APPROVALS (full width) ====== -->
            <div style="margin-top:22px;">
                <div class="section-hdr">
                    <div class="section-title">
                        Pending Account Approvals
                        <span class="pending-badge"
                              style="margin-left:8px;vertical-align:middle;font-size:11px;">2</span>
                    </div>
                    <a href="<%= request.getContextPath() %>/manageAccounts?filter=pending"
                       class="btn btn-outline btn-sm">View All Pending</a>
                </div>

                <div class="alert alert-warning">
                    ⏰ 2 new account registrations are awaiting your approval.
                </div>

                <div class="table-wrap">
                    <table>
                        <thead>
                        <tr>
                            <th>Applicant</th>
                            <th>Account No.</th>
                            <th>Type</th>
                            <th>Email</th>
                            <th>Registered On</th>
                            <th>Waiting</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div style="font-weight:500;">Priya Sharma</div>
                                <div style="font-size:11px;color:var(--text3);">Savings Account</div>
                            </td>
                            <td>ACC-0248</td>
                            <td>Current</td>
                            <td style="font-size:12px;color:var(--text2);">priya@email.com</td>
                            <td>02 May 2026</td>
                            <td><span class="pill pill-amber">1 day</span></td>
                            <td>
                                <div style="display:flex;gap:6px;">
                                    <a href="<%= request.getContextPath() %>/approveAccount?accId=ACC-0248"
                                       class="btn btn-success btn-sm">✓ Approve</a>
                                    <a href="<%= request.getContextPath() %>/rejectAccount?accId=ACC-0248"
                                       class="btn btn-danger btn-sm">✕ Reject</a>
                                    <a href="<%= request.getContextPath() %>/viewAccount?accId=ACC-0248"
                                       class="btn btn-gray btn-sm">👁 View</a>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div style="font-weight:500;">Sita Thapa</div>
                                <div style="font-size:11px;color:var(--text3);">Savings Account</div>
                            </td>
                            <td>ACC-0249</td>
                            <td>Savings</td>
                            <td style="font-size:12px;color:var(--text2);">sita@email.com</td>
                            <td>03 May 2026</td>
                            <td><span class="pill pill-red">3 days</span></td>
                            <td>
                                <div style="display:flex;gap:6px;">
                                    <a href="<%= request.getContextPath() %>/approveAccount?accId=ACC-0249"
                                       class="btn btn-success btn-sm">✓ Approve</a>
                                    <a href="<%= request.getContextPath() %>/rejectAccount?accId=ACC-0249"
                                       class="btn btn-danger btn-sm">✕ Reject</a>
                                    <a href="<%= request.getContextPath() %>/viewAccount?accId=ACC-0249"
                                       class="btn btn-gray btn-sm">👁 View</a>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div><!-- /pending-account-approvals -->

        </div><!-- /page-body -->
    </div><!-- /main -->
</div><!-- /app -->

<script>
    // Highlight active nav link based on current servlet path
    (function () {
        var path = window.location.pathname;
        var map = {
            '/adminDashboard': 'nav-home',
            '/manageAccounts': 'nav-accounts',
            '/pendingApprovals': 'nav-pending-accounts',
            '/cardApplications': 'nav-card-apps',
            '/issueCard': 'nav-issue-card',
            '/allTransactions': 'nav-all-txns',
            '/failedOperations': 'nav-failed-ops'
        };
        document.querySelectorAll('.nav-link').forEach(function (el) {
            el.classList.remove('active');
        });
        for (var key in map) {
            if (path.indexOf(key) !== -1) {
                var el = document.getElementById(map[key]);
                if (el) el.classList.add('active');
                break;
            }
        }
    })();
</script>

</body>
</html>
