<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create Account</title>
    <style>
        body {
            margin: 0;
            font-family: "Segoe UI", sans-serif;
            background: linear-gradient(135deg, #1e293b, #0f172a);
            color: #e2e8f0;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            background: #1e293b;
            padding: 30px;
            border-radius: 12px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        h3 {
            margin-top: 20px;
            font-size: 14px;
            color: #94a3b8;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-top: 8px;
            border-radius: 6px;
            border: none;
            background: #0f172a;
            color: white;
        }

        button {
            width: 100%;
            margin-top: 20px;
            padding: 12px;
            border: none;
            background: #3b82f6;
            color: white;
            border-radius: 8px;
            cursor: pointer;
        }

        .error {
            background: #7f1d1d;
            padding: 10px;
            border-radius: 6px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div class="container">

    <h2>Create Bank Account</h2>

    <% if (request.getAttribute("error") != null) { %>
    <div class="error"><%= request.getAttribute("error") %>
    </div>
    <% } %>

    <form action="<%=request.getContextPath()%>/account" method="post">

        <h3>Account</h3>
        <select name="accountType" required>
            <option value="">Select Type</option>
            <option value="SAVINGS">Savings</option>
            <option value="CURRENT">Current</option>
        </select>

        <h3>KYC</h3>
        <input type="date" name="dob" required>
        <input type="text" name="gender" placeholder="Gender" required>
        <input type="text" name="citizenship" placeholder="Citizenship No" required>
        <input type="date" name="citizenshipIssueDate" required>
        <input type="text" name="citizenshipDistrict" placeholder="District" required>
        <input type="text" name="phone" placeholder="Phone" required>
        <input type="text" name="occupation" placeholder="Occupation">
        <input type="number" name="income" placeholder="Income">

        <h3>Address</h3>
        <input type="text" name="province" placeholder="Province">
        <input type="text" name="district" placeholder="District">
        <input type="text" name="city" placeholder="City">
        <input type="number" name="ward" placeholder="Ward">
        <input type="text" name="tole" placeholder="Tole">

        <h3>Family</h3>
        <input type="text" name="fatherName" placeholder="Father Name">
        <input type="text" name="motherName" placeholder="Mother Name">

        <button type="submit">Create Account</button>

    </form>

</div>

</body>
</html>