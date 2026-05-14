<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Server Error — Atomic Bank</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700;800&display=swap" rel="stylesheet">
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Inter, sans-serif; background: #f0f4fb; color: #0f1d2e; display: flex; align-items: center; justify-content: center; min-height: 100vh; }
        .error-box { text-align: center; padding: 48px 40px; background: #fff; border-radius: 20px; box-shadow: 0 4px 32px rgba(15,29,46,0.10); max-width: 480px; width: 90%; }
        .error-code { font-size: 96px; font-weight: 800; color: #dc2626; letter-spacing: -0.04em; line-height: 1; }
        .error-title { font-size: 24px; font-weight: 700; margin: 16px 0 10px; }
        .error-desc { color: #5a6e8a; font-size: 15px; line-height: 1.7; margin-bottom: 28px; }
        .btn { display: inline-block; padding: 12px 28px; background: #2563eb; color: #fff; border-radius: 10px; font-weight: 700; font-size: 14px; text-decoration: none; }
        .btn:hover { background: #1d4ed8; }
    </style>
</head>
<body>
<div class="error-box">
    <div class="error-code">500</div>
    <h1 class="error-title">Something Went Wrong</h1>
    <p class="error-desc">An internal server error occurred. Please try again or contact support if the issue persists.</p>
    <a href="javascript:history.back()" class="btn">← Go Back</a>
</div>
</body>
</html>
