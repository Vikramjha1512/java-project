<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.login.model.User" %>
<%
    // Session validation
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        
        .container {
            max-width: 800px;
            margin: 50px auto;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            padding: 40px;
        }
        
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 2px solid #eee;
        }
        
        h2 {
            color: #333;
            font-size: 28px;
        }
        
        .logout-btn {
            padding: 10px 20px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        
        .logout-btn:hover {
            background-color: #c82333;
        }
        
        .welcome-box {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            border-radius: 10px;
            margin-bottom: 30px;
            text-align: center;
        }
        
        .welcome-box h3 {
            font-size: 24px;
            margin-bottom: 10px;
        }
        
        .info-box {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        
        .info-box h4 {
            color: #555;
            margin-bottom: 15px;
        }
        
        .info-item {
            display: flex;
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
        }
        
        .info-item:last-child {
            border-bottom: none;
        }
        
        .info-label {
            font-weight: bold;
            color: #666;
            width: 120px;
        }
        
        .info-value {
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>User Dashboard</h2>
            <a href="logout" class="logout-btn">Logout</a>
        </div>
        
        <div class="welcome-box">
            <h3>Welcome, <%= user.getName() %>! 👋</h3>
            <p>You have successfully logged in to your account.</p>
        </div>
        
        <div class="info-box">
            <h4>Your Account Information</h4>
            
            <div class="info-item">
                <span class="info-label">User ID:</span>
                <span class="info-value"><%= user.getUserId() %></span>
            </div>
            
            <div class="info-item">
                <span class="info-label">Name:</span>
                <span class="info-value"><%= user.getName() %></span>
            </div>
            
            <div class="info-item">
                <span class="info-label">Email:</span>
                <span class="info-value"><%= user.getEmail() %></span>
            </div>
            
            <div class="info-item">
                <span class="info-label">Role:</span>
                <span class="info-value"><%= user.getRole() %></span>
            </div>
        </div>
    </div>
</body>
</html>
