<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>

<h2>Admin Dashboard</h2>

<%
    // Always safe way to get context path
    String ctx = request.getContextPath();
%>

<a href="<%= ctx %>/admin/pending-accounts">
    ▶ View Pending Account Approvals
</a>

</body>
</html>