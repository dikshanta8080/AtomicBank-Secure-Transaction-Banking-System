<%--
  AtomicBank - Pending Approval Page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>AtomicBank – Pending Approval</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display&family=DM+Sans:wght@400;500&display=swap"
          rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'DM Sans', sans-serif;
            background: #f5f7f6;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .card {
            background: #fff;
            border-radius: 20px;
            padding: 3rem 2.5rem;
            max-width: 440px;
            width: 90%;
            text-align: center;
            border-top: 4px solid #1D9E75;
            box-shadow: 0 4px 24px rgba(0, 0, 0, 0.07);
        }


        .logo {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            margin-bottom: 2rem;
        }

        .logo-icon {
            width: 32px;
            height: 32px;
            background: #1D9E75;
            border-radius: 8px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .logo-name {
            font-family: 'DM Serif Display', serif;
            font-size: 20px;
            color: #111;
        }


        .icon-ring {
            width: 88px;
            height: 88px;
            border-radius: 50%;
            background: #E1F5EE;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 1.5rem;
            animation: pulse 2.4s ease-in-out infinite;
        }

        @keyframes pulse {
            0%, 100% {
                box-shadow: 0 0 0 0 rgba(29, 158, 117, 0.2);
            }
            50% {
                box-shadow: 0 0 0 14px rgba(29, 158, 117, 0);
            }
        }


        .badge {
            display: inline-flex;
            align-items: center;
            gap: 6px;
            background: #E1F5EE;
            color: #0F6E56;
            font-size: 12px;
            font-weight: 500;
            padding: 6px 14px;
            border-radius: 20px;
            margin-bottom: 1.25rem;
        }

        .dot {
            width: 7px;
            height: 7px;
            border-radius: 50%;
            background: #1D9E75;
            animation: blink 1.4s infinite;
        }

        @keyframes blink {
            0%, 100% {
                opacity: 1;
            }
            50% {
                opacity: 0.3;
            }
        }

        h1 {
            font-family: 'DM Serif Display', serif;
            font-size: 24px;
            color: #111;
            margin-bottom: 0.6rem;
        }

        .sub {
            font-size: 14px;
            color: #666;
            line-height: 1.6;
            margin-bottom: 2rem;
        }


        .steps {
            text-align: left;
            margin-bottom: 2rem;
        }

        .step {
            display: flex;
            align-items: flex-start;
            gap: 12px;
            padding: 12px 8px;
        }

        .step-num {
            width: 28px;
            height: 28px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 12px;
            font-weight: 500;
            flex-shrink: 0;
        }

        .done {
            background: #E1F5EE;
            color: #0F6E56;
        }

        .active {
            background: #1D9E75;
            color: #fff;
        }

        .idle {
            background: #f0f0f0;
            color: #999;
        }

        .connector {
            width: 1px;
            height: 10px;
            background: #e0e0e0;
            margin-left: 21px;
        }

        .step-label {
            font-size: 13px;
            font-weight: 500;
            color: #222;
            margin-bottom: 2px;
        }

        .step-desc {
            font-size: 12px;
            color: #888;
        }


        .footer {
            font-size: 12px;
            color: #aaa;
            border-top: 1px solid #f0f0f0;
            padding-top: 1.25rem;
        }

        .footer a {
            color: #1D9E75;
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="card">


    <div class="logo">
        <div class="logo-icon">
            <svg width="18" height="18" viewBox="0 0 18 18" fill="none">
                <circle cx="9" cy="9" r="5" stroke="white" stroke-width="2"/>
                <circle cx="9" cy="9" r="1.5" fill="white"/>
            </svg>
        </div>
        <span class="logo-name">AtomicBank</span>
    </div>


    <div class="icon-ring">
        <svg width="40" height="40" viewBox="0 0 40 40" fill="none">
            <circle cx="20" cy="20" r="13" stroke="#5DCAA5" stroke-width="1.5" stroke-dasharray="4 3"/>
            <path d="M14 21L18 25L26 16" stroke="#1D9E75" stroke-width="2.5" stroke-linecap="round"
                  stroke-linejoin="round"/>
        </svg>
    </div>


    <div class="badge">
        <span class="dot"></span>
        Verification in progress
    </div>

    <h1>Your account is under review</h1>
    <p class="sub">
        We're verifying your details to keep AtomicBank safe and secure.<br>
        This usually takes <strong>1–2 business days</strong>.
    </p>


    <div class="steps">
        <div class="step">
            <div class="step-num done">
                <svg width="12" height="12" viewBox="0 0 12 12" fill="none">
                    <path d="M2.5 6L5 8.5L9.5 3.5" stroke="#0F6E56" stroke-width="1.8" stroke-linecap="round"
                          stroke-linejoin="round"/>
                </svg>
            </div>
            <div>
                <div class="step-label">Application submitted</div>
                <div class="step-desc">Your details have been received</div>
            </div>
        </div>
        <div class="connector"></div>
        <div class="step">
            <div class="step-num active">2</div>
            <div>
                <div class="step-label">Identity verification</div>
                <div class="step-desc">Our team is reviewing your documents</div>
            </div>
        </div>
        <div class="connector"></div>
        <div class="step">
            <div class="step-num idle">3</div>
            <div>
                <div class="step-label">Account activation</div>
                <div class="step-desc">You'll receive an email once approved</div>
            </div>
        </div>
    </div>

    <div class="footer">
        Questions? <a href="#">Contact support</a> &nbsp;·&nbsp; <a href="#">Learn about verification</a>
    </div>

</div>

</body>
</html>
