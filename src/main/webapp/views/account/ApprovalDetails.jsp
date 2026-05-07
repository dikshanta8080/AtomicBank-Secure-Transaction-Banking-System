<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.banking.sathi.dto.response.AccountDetailDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Review — AtomicBank</title>
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
            max-width: 860px;
            margin: 0 auto;
            padding: 2.5rem 2rem 4rem;
        }

        .back-link {
            display: inline-flex;
            align-items: center;
            gap: 6px;
            font-size: 13px;
            color: var(--text-muted);
            text-decoration: none;
            margin-bottom: 1.75rem;
        }

        .back-link:hover {
            color: var(--text);
        }

        .hero {
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: var(--radius);
            padding: 1.75rem;
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 1.25rem;
            box-shadow: var(--shadow);
            flex-wrap: wrap;
            gap: 1rem;
        }

        .hero-left {
            display: flex;
            align-items: center;
            gap: 16px;
        }

        .avatar {
            width: 52px;
            height: 52px;
            border-radius: 50%;
            background: var(--bg);
            border: 1px solid var(--border);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 18px;
            font-weight: 500;
            color: var(--text-muted);
            flex-shrink: 0;
        }

        .hero-name {
            font-size: 20px;
            font-weight: 500;
            letter-spacing: -0.02em;
            margin-bottom: 2px;
        }

        .hero-sub {
            font-size: 13px;
            color: var(--text-muted);
            font-family: 'DM Mono', monospace;
        }

        .hero-badges {
            display: flex;
            gap: 8px;
            flex-wrap: wrap;
        }

        .badge {
            display: inline-flex;
            align-items: center;
            gap: 4px;
            font-size: 11px;
            font-weight: 500;
            padding: 3px 9px;
            border-radius: 99px;
            white-space: nowrap;
        }

        .badge-dot {
            width: 5px;
            height: 5px;
            border-radius: 50%;
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

        .grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1.25rem;
            margin-bottom: 1.25rem;
        }

        .grid-full {
            grid-column: 1 / -1;
        }

        .card {
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: var(--radius);
            padding: 1.25rem 1.5rem;
            box-shadow: var(--shadow);
        }

        .card-title {
            font-size: 10px;
            font-weight: 500;
            letter-spacing: 0.08em;
            text-transform: uppercase;
            color: var(--text-hint);
            margin-bottom: 1rem;
        }

        .field-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 8px 0;
            border-bottom: 1px solid var(--border);
        }

        .field-row:last-child {
            border-bottom: none;
        }

        .field-key {
            font-size: 13px;
            color: var(--text-muted);
        }

        .field-val {
            font-size: 13px;
            font-weight: 500;
            color: var(--text);
            text-align: right;
        }

        .field-val.mono {
            font-family: 'DM Mono', monospace;
            font-weight: 400;
            font-size: 12px;
        }

        /* Action bar */
        .action-bar {
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: var(--radius);
            padding: 1.25rem 1.5rem;
            display: flex;
            align-items: center;
            justify-content: space-between;
            box-shadow: var(--shadow);
            gap: 1rem;
            flex-wrap: wrap;
        }

        .action-label {
            font-size: 13px;
            color: var(--text-muted);
        }

        .action-label strong {
            color: var(--text);
            font-weight: 500;
        }

        .action-forms {
            display: flex;
            gap: 10px;
        }

        .btn-approve, .btn-reject, .btn-back {
            font-family: 'DM Sans', sans-serif;
            font-size: 13px;
            font-weight: 500;
            padding: 9px 22px;
            border-radius: var(--radius-sm);
            cursor: pointer;
            border: 1px solid transparent;
        }

        .btn-approve {
            background: var(--text);
            color: #fff;
            border-color: var(--text);
        }

        .btn-approve:hover {
            opacity: 0.82;
        }

        .btn-reject {
            background: transparent;
            color: var(--failed-fg);
            border-color: var(--border);
        }

        .btn-reject:hover {
            background: var(--failed-bg);
            border-color: var(--failed-fg);
        }
    </style>
</head>
<body>

<%
    AccountDetailDTO d = (AccountDetailDTO) request.getAttribute("accountDetail");
    String ctx = request.getContextPath();

    if (d == null) {
        response.sendRedirect(ctx + "/admin/pending-accounts");
        return;
    }

    String name = d.getName() != null ? d.getName() : "—";
    String email = d.getEmail() != null ? d.getEmail() : "—";
    String phone = d.getPhone() != null ? d.getPhone() : "—";
    String kyc = d.getKycStatus() != null ? d.getKycStatus().name() : "—";
    String type = d.getAccountType() != null ? d.getAccountType().name() : "—";
    String status = d.getAccountStatus() != null ? d.getAccountStatus().name() : "—";

    String[] parts = name.trim().split("\\s+");
    String initials = parts.length >= 2
            ? ("" + parts[0].charAt(0) + parts[parts.length - 1].charAt(0)).toUpperCase()
            : name.length() > 0 ? name.substring(0, 1).toUpperCase() : "?";

    String kycBadge = "VERIFIED".equals(kyc) ? "badge-verified" : "FAILED".equals(kyc) ? "badge-failed" : "badge-pending";
    String typeBadge = "SAVINGS".equals(type) ? "badge-savings" : "badge-checking";
    String statusBadge = "ACTIVE".equals(status) ? "badge-active" : "FROZEN".equals(status) ? "badge-frozen" : "badge-inactive";
%>

<div class="page">

    <a href="<%= ctx %>/admin/pending-accounts" class="back-link">
        ← Back to pending accounts
    </a>

    <!-- Hero -->
    <div class="hero">
        <div class="hero-left">
            <div class="avatar"><%= initials %>
            </div>
            <div>
                <div class="hero-name"><%= name %>
                </div>
                <div class="hero-sub"><%= d.getAccountNumber() != null ? d.getAccountNumber() : "No account number" %>
                </div>
            </div>
        </div>
        <div class="hero-badges">
            <span class="badge <%= kycBadge %>"><span class="badge-dot"></span>KYC: <%= kyc %></span>
            <span class="badge <%= typeBadge %>"><%= type %></span>
            <span class="badge <%= statusBadge %>"><span class="badge-dot"></span><%= status %></span>
        </div>
    </div>

    <div class="grid">

        <!-- Personal -->
        <div class="card">
            <div class="card-title">Personal information</div>
            <div class="field-row"><span class="field-key">Full name</span><span class="field-val"><%= name %></span>
            </div>
            <div class="field-row"><span class="field-key">Date of birth</span><span
                    class="field-val mono"><%= d.getDob() != null ? d.getDob() : "—" %></span></div>
            <div class="field-row"><span class="field-key">Gender</span><span
                    class="field-val"><%= d.getGender() != null ? d.getGender() : "—" %></span></div>
            <div class="field-row"><span class="field-key">Citizenship no.</span><span
                    class="field-val mono"><%= d.getCitizenship() != null ? d.getCitizenship() : "—" %></span></div>
            <div class="field-row"><span class="field-key">Occupation</span><span
                    class="field-val"><%= d.getOccupation() != null ? d.getOccupation() : "—" %></span></div>
        </div>

        <!-- Contact -->
        <div class="card">
            <div class="card-title">Contact</div>
            <div class="field-row"><span class="field-key">Email</span><span class="field-val mono"><%= email %></span>
            </div>
            <div class="field-row"><span class="field-key">Phone</span><span class="field-val mono"><%= phone %></span>
            </div>
        </div>

        <!-- Address -->
        <div class="card">
            <div class="card-title">Address</div>
            <div class="field-row"><span class="field-key">Province</span><span
                    class="field-val"><%= d.getProvince() != null ? d.getProvince() : "—" %></span></div>
            <div class="field-row"><span class="field-key">District</span><span
                    class="field-val"><%= d.getDistrict() != null ? d.getDistrict() : "—" %></span></div>
            <div class="field-row"><span class="field-key">City</span><span
                    class="field-val"><%= d.getCity() != null ? d.getCity() : "—" %></span></div>
            <div class="field-row"><span class="field-key">Ward</span><span
                    class="field-val mono"><%= d.getWard() %></span></div>
            <div class="field-row"><span class="field-key">Tole</span><span
                    class="field-val"><%= d.getTole() != null ? d.getTole() : "—" %></span></div>
        </div>

        <!-- Family -->
        <div class="card">
            <div class="card-title">Family</div>
            <div class="field-row"><span class="field-key">Father's name</span><span
                    class="field-val"><%= d.getFatherName() != null ? d.getFatherName() : "—" %></span></div>
            <div class="field-row"><span class="field-key">Mother's name</span><span
                    class="field-val"><%= d.getMotherName() != null ? d.getMotherName() : "—" %></span></div>
        </div>

        <!-- Account -->
        <div class="card grid-full">
            <div class="card-title">Account details</div>
            <div class="field-row"><span class="field-key">User ID</span><span
                    class="field-val mono"><%= d.getUserId() %></span></div>
            <div class="field-row"><span class="field-key">Account number</span><span
                    class="field-val mono"><%= d.getAccountNumber() != null ? d.getAccountNumber() : "—" %></span></div>
            <div class="field-row"><span class="field-key">Account type</span><span class="field-val"><%= type %></span>
            </div>
            <div class="field-row"><span class="field-key">Account status</span><span
                    class="field-val"><%= status %></span></div>
            <div class="field-row"><span class="field-key">KYC status</span><span class="field-val"><%= kyc %></span>
            </div>
        </div>

    </div>

    <!-- Action bar -->
    <div class="action-bar">
        <div class="action-label">Reviewing account for <strong><%= name %>
        </strong></div>
        <div class="action-forms">
            <form method="POST" action="<%= ctx %>/admin/reject-account">
                <input type="hidden" name="userId" value="<%= d.getUserId() %>">
                <button type="submit" class="btn-reject">Reject</button>
            </form>
            <form method="POST" action="<%= ctx %>/admin/approve-account">
                <input type="hidden" name="userId" value="<%= d.getUserId() %>">
                <button type="submit" class="btn-approve">Approve</button>
            </form>
        </div>
    </div>

</div>

</body>
</html>
