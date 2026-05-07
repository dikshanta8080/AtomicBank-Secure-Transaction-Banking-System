<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.banking.sathi.dto.response.AccountListDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Approvals — AtomicBank</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500&family=DM+Mono:wght@400;500&display=swap"
          rel="stylesheet">
    <style>
        *, *::before, *::after {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        :root {
            --bg: #F7F6F3;
            --surface: #FFFFFF;
            --border: #E4E2DC;
            --border-hover: #C8C5BC;
            --text: #1A1916;
            --text-muted: #787570;
            --text-hint: #B0ADA6;
            --pending-bg: #FEF3E2;
            --pending-fg: #92400E;
            --verified-bg: #ECFDF5;
            --verified-fg: #065F46;
            --failed-bg: #FEF2F2;
            --failed-fg: #991B1B;
            --savings-bg: #EFF6FF;
            --savings-fg: #1E40AF;
            --checking-bg: #F5F3FF;
            --checking-fg: #5B21B6;
            --radius: 10px;
            --radius-sm: 6px;
            --shadow: 0 1px 3px rgba(0, 0, 0, 0.06);
        }

        body {
            font-family: 'DM Sans', sans-serif;
            background: var(--bg);
            color: var(--text);
            min-height: 100vh;
            font-size: 14px;
        }

        .page {
            max-width: 1100px;
            margin: 0 auto;
            padding: 2.5rem 2rem;
        }

        .eyebrow {
            font-size: 11px;
            font-weight: 500;
            letter-spacing: 0.08em;
            text-transform: uppercase;
            color: var(--text-hint);
            margin-bottom: 4px;
        }

        .page-title {
            font-size: 26px;
            font-weight: 500;
            letter-spacing: -0.02em;
            margin-bottom: 2rem;
        }

        .stats {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 12px;
            margin-bottom: 1.75rem;
        }

        .stat-card {
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: var(--radius);
            padding: 1.1rem 1.25rem;
            box-shadow: var(--shadow);
        }

        .stat-label {
            font-size: 12px;
            color: var(--text-muted);
            margin-bottom: 6px;
        }

        .stat-value {
            font-size: 28px;
            font-weight: 300;
            font-family: 'DM Mono', monospace;
            letter-spacing: -0.03em;
        }

        /* Filter form */
        .filter-form {
            display: flex;
            gap: 10px;
            margin-bottom: 1rem;
            align-items: center;
        }

        .filter-form input[type="text"],
        .filter-form select {
            font-family: 'DM Sans', sans-serif;
            font-size: 13px;
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: var(--radius-sm);
            color: var(--text);
            outline: none;
            box-shadow: var(--shadow);
            padding: 9px 12px;
        }

        .filter-form input[type="text"] {
            flex: 1;
        }

        .filter-form input[type="text"]:focus,
        .filter-form select:focus {
            border-color: var(--border-hover);
        }

        .btn-filter {
            font-family: 'DM Sans', sans-serif;
            font-size: 13px;
            font-weight: 500;
            padding: 9px 18px;
            background: var(--text);
            color: #fff;
            border: none;
            border-radius: var(--radius-sm);
            cursor: pointer;
        }

        .btn-filter:hover {
            opacity: 0.85;
        }

        .btn-reset {
            font-family: 'DM Sans', sans-serif;
            font-size: 13px;
            padding: 9px 14px;
            background: transparent;
            color: var(--text-muted);
            border: 1px solid var(--border);
            border-radius: var(--radius-sm);
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }

        .btn-reset:hover {
            background: var(--bg);
        }

        .count-bar {
            font-size: 12px;
            color: var(--text-hint);
            margin-bottom: 10px;
        }

        .count-bar strong {
            color: var(--text);
            font-weight: 500;
        }

        .table-card {
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: var(--radius);
            overflow: hidden;
            box-shadow: var(--shadow);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            table-layout: fixed;
        }

        thead {
            background: var(--bg);
        }

        th {
            padding: 10px 14px;
            font-size: 11px;
            font-weight: 500;
            color: var(--text-hint);
            text-align: left;
            letter-spacing: 0.06em;
            text-transform: uppercase;
            border-bottom: 1px solid var(--border);
            white-space: nowrap;
        }

        td {
            padding: 12px 14px;
            border-bottom: 1px solid var(--border);
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            vertical-align: middle;
        }

        tbody tr:last-child td {
            border-bottom: none;
        }

        tbody tr:hover {
            background: var(--bg);
        }

        .col-id {
            width: 52px;
        }

        .col-name {
            width: 160px;
        }

        .col-email {
            width: 175px;
        }

        .col-phone {
            width: 115px;
        }

        .col-kyc {
            width: 100px;
        }

        .col-type {
            width: 95px;
        }

        .col-status {
            width: 95px;
        }

        .col-actions {
            width: 210px;
        }

        .id-num {
            font-family: 'DM Mono', monospace;
            font-size: 12px;
            color: var(--text-hint);
        }

        .name-wrap {
            display: flex;
            align-items: center;
            gap: 9px;
        }

        .avatar {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            background: var(--bg);
            border: 1px solid var(--border);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 11px;
            font-weight: 500;
            color: var(--text-muted);
            flex-shrink: 0;
        }

        .name-text {
            font-weight: 500;
        }

        .email-text {
            color: var(--text-muted);
            font-size: 13px;
        }

        .phone-text {
            font-family: 'DM Mono', monospace;
            font-size: 12px;
            color: var(--text-muted);
        }

        .badge {
            display: inline-flex;
            align-items: center;
            gap: 4px;
            font-size: 11px;
            font-weight: 500;
            padding: 3px 8px;
            border-radius: 99px;
            white-space: nowrap;
        }

        .badge-dot {
            width: 5px;
            height: 5px;
            border-radius: 50%;
            flex-shrink: 0;
        }

        .badge-pending {
            background: var(--pending-bg);
            color: var(--pending-fg);
        }

        .badge-pending .badge-dot {
            background: var(--pending-fg);
        }

        .badge-verified {
            background: var(--verified-bg);
            color: var(--verified-fg);
        }

        .badge-verified .badge-dot {
            background: var(--verified-fg);
        }

        .badge-failed {
            background: var(--failed-bg);
            color: var(--failed-fg);
        }

        .badge-failed .badge-dot {
            background: var(--failed-fg);
        }

        .badge-savings {
            background: var(--savings-bg);
            color: var(--savings-fg);
        }

        .badge-checking {
            background: var(--checking-bg);
            color: var(--checking-fg);
        }

        .badge-inactive {
            background: var(--pending-bg);
            color: var(--pending-fg);
        }

        .badge-active {
            background: var(--verified-bg);
            color: var(--verified-fg);
        }

        .badge-frozen {
            background: var(--failed-bg);
            color: var(--failed-fg);
        }

        .row-actions {
            display: flex;
            gap: 6px;
            align-items: center;
        }

        .btn-view {
            font-family: 'DM Sans', sans-serif;
            font-size: 12px;
            font-weight: 500;
            padding: 5px 11px;
            border-radius: var(--radius-sm);
            cursor: pointer;
            border: 1px solid var(--border);
            background: transparent;
            color: var(--text-muted);
            text-decoration: none;
            display: inline-block;
        }

        .btn-view:hover {
            background: var(--text);
            border-color: var(--text);
            color: #fff;
        }

        .btn-approve, .btn-reject {
            font-family: 'DM Sans', sans-serif;
            font-size: 12px;
            font-weight: 500;
            padding: 5px 11px;
            border-radius: var(--radius-sm);
            cursor: pointer;
            border: 1px solid var(--border);
            background: transparent;
        }

        .btn-approve {
            color: var(--verified-fg);
        }

        .btn-approve:hover {
            background: var(--verified-bg);
            border-color: var(--verified-fg);
        }

        .btn-reject {
            color: var(--failed-fg);
        }

        .btn-reject:hover {
            background: var(--failed-bg);
            border-color: var(--failed-fg);
        }

        .empty {
            text-align: center;
            padding: 4rem;
            color: var(--text-hint);
            font-size: 13px;
        }
    </style>
</head>
<body>

<%
    List<AccountListDTO> allAccounts = (List<AccountListDTO>) request.getAttribute("accounts");
    if (allAccounts == null) allAccounts = new ArrayList<>();
    String ctx = request.getContextPath();

    /* ── Server-side filtering ── */
    String searchParam = request.getParameter("search") != null ? request.getParameter("search").trim() : "";
    String kycParam = request.getParameter("kyc") != null ? request.getParameter("kyc").trim() : "";
    String typeParam = request.getParameter("type") != null ? request.getParameter("type").trim() : "";

    List<AccountListDTO> accounts = new ArrayList<>();
    for (AccountListDTO a : allAccounts) {
        String name = a.getName() != null ? a.getName().toLowerCase() : "";
        String email = a.getEmail() != null ? a.getEmail().toLowerCase() : "";
        String kyc = a.getKycStatus() != null ? a.getKycStatus().name() : "";
        String type = a.getAccountType() != null ? a.getAccountType().name() : "";

        boolean matchSearch = searchParam.isEmpty() || name.contains(searchParam.toLowerCase()) || email.contains(searchParam.toLowerCase());
        boolean matchKyc = kycParam.isEmpty() || kyc.equalsIgnoreCase(kycParam);
        boolean matchType = typeParam.isEmpty() || type.equalsIgnoreCase(typeParam);

        if (matchSearch && matchKyc && matchType) accounts.add(a);
    }

    /* ── Stats (from full list) ── */
    int total = allAccounts.size(), kycVerified = 0, kycFailed = 0;
    for (AccountListDTO a : allAccounts) {
        String k = a.getKycStatus() != null ? a.getKycStatus().name() : "";
        if ("VERIFIED".equals(k)) kycVerified++;
        else if ("FAILED".equals(k)) kycFailed++;
    }
%>

<div class="page">
    <div class="eyebrow">Admin · AtomicBank</div>
    <div class="page-title">Pending Approvals</div>

    <div class="stats">
        <div class="stat-card">
            <div class="stat-label">Total pending</div>
            <div class="stat-value"><%= total %>
            </div>
        </div>
        <div class="stat-card">
            <div class="stat-label">KYC verified</div>
            <div class="stat-value"><%= kycVerified %>
            </div>
        </div>
        <div class="stat-card">
            <div class="stat-label">KYC failed</div>
            <div class="stat-value"><%= kycFailed %>
            </div>
        </div>
    </div>

    <!-- Filter form — GET so filters appear in URL -->
    <form method="GET" action="<%= ctx %>/admin/pending-accounts" class="filter-form">
        <input type="text" name="search" placeholder="Search by name or email…" value="<%= searchParam %>">
        <select name="kyc">
            <option value="">All KYC</option>
            <option value="VERIFIED" <%= "VERIFIED".equals(kycParam) ? "selected" : "" %>>Verified</option>
            <option value="PENDING"  <%= "PENDING".equals(kycParam) ? "selected" : "" %>>Pending</option>
            <option value="FAILED"   <%= "FAILED".equals(kycParam) ? "selected" : "" %>>Failed</option>
        </select>
        <select name="type">
            <option value="">All types</option>
            <option value="SAVINGS"  <%= "SAVINGS".equals(typeParam) ? "selected" : "" %>>Savings</option>
            <option value="CHECKING" <%= "CHECKING".equals(typeParam) ? "selected" : "" %>>Checking</option>
        </select>
        <button type="submit" class="btn-filter">Filter</button>
        <a href="<%= ctx %>/admin/pending-accounts" class="btn-reset">Reset</a>
    </form>

    <div class="count-bar">
        Showing <strong><%= accounts.size() %>
    </strong> of <strong><%= total %>
    </strong> accounts
    </div>

    <div class="table-card">
        <table>
            <thead>
            <tr>
                <th class="col-id">ID</th>
                <th class="col-name">Name</th>
                <th class="col-email">Email</th>
                <th class="col-phone">Phone</th>
                <th class="col-kyc">KYC</th>
                <th class="col-type">Type</th>
                <th class="col-status">Status</th>
                <th class="col-actions">Actions</th>
            </tr>
            </thead>
            <tbody>
            <% if (accounts.isEmpty()) { %>
            <tr>
                <td colspan="8" class="empty">No accounts match your filters.</td>
            </tr>
            <% } else {
                for (AccountListDTO acc : accounts) {
                    String kyc = acc.getKycStatus() != null ? acc.getKycStatus().name() : "—";
                    String type = acc.getAccountType() != null ? acc.getAccountType().name() : "—";
                    String status = acc.getAccountStatus() != null ? acc.getAccountStatus().name() : "—";
                    String name = acc.getName() != null ? acc.getName() : "—";
                    String email = acc.getEmail() != null ? acc.getEmail() : "—";
                    String phone = acc.getPhone() != null ? acc.getPhone() : "—";

                    String[] parts = name.trim().split("\\s+");
                    String initials = parts.length >= 2
                            ? ("" + parts[0].charAt(0) + parts[parts.length - 1].charAt(0)).toUpperCase()
                            : name.substring(0, 1).toUpperCase();

                    String kycBadge = "VERIFIED".equals(kyc) ? "badge-verified" : "FAILED".equals(kyc) ? "badge-failed" : "badge-pending";
                    String typeBadge = "SAVINGS".equals(type) ? "badge-savings" : "badge-checking";
                    String statusBadge = "ACTIVE".equals(status) ? "badge-active" : "FROZEN".equals(status) ? "badge-frozen" : "badge-inactive";
            %>
            <tr>
                <td class="col-id"><span class="id-num"><%= acc.getUserId() %></span></td>
                <td class="col-name">
                    <div class="name-wrap">
                        <div class="avatar"><%= initials %>
                        </div>
                        <span class="name-text"><%= name %></span>
                    </div>
                </td>
                <td class="col-email"><span class="email-text"><%= email %></span></td>
                <td class="col-phone"><span class="phone-text"><%= phone %></span></td>
                <td class="col-kyc"><span class="badge <%= kycBadge %>"><span class="badge-dot"></span><%= kyc %></span>
                </td>
                <td class="col-type"><span class="badge <%= typeBadge %>"><%= type %></span></td>
                <td class="col-status"><span class="badge <%= statusBadge %>"><span
                        class="badge-dot"></span><%= status %></span></td>
                <td class="col-actions">
                    <div class="row-actions">
                        <a href="<%= ctx %>/admin/approval-details?userId=<%= acc.getUserId() %>"
                           class="btn-view">View</a>

                        <form method="POST" action="<%= ctx %>/admin/approve-account">
                            <input type="hidden" name="userId" value="<%= acc.getUserId() %>">
                            <button type="submit" class="btn-approve">Approve</button>
                        </form>

                        <form method="POST" action="<%= ctx %>/admin/reject-account">
                            <input type="hidden" name="userId" value="<%= acc.getUserId() %>">
                            <button type="submit" class="btn-reject">Reject</button>
                        </form>
                    </div>
                </td>
            </tr>
            <% }
            } %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
