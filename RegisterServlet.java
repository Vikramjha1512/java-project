package com.login.controller;

import com.login.model.User;
import com.login.util.DBConnection;
import com.login.util.PasswordUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    // Email validation pattern
    private static final String EMAIL_PATTERN = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    
    public RegisterServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // Get form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // Validate input
        String validationError = validateInput(name, email, password, confirmPassword);
        
        if (validationError != null) {
            request.setAttribute("error", validationError);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        // Hash password
        String hashedPassword = PasswordUtils.hashPassword(password);
        
        // Insert into database
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "INSERT INTO User (name, email, password, role) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, name.trim());
            pstmt.setString(2, email.trim().toLowerCase());
            pstmt.setString(3, hashedPassword);
            pstmt.setString(4, "User");
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                response.sendRedirect(request.getContextPath() + "/login.jsp?success=registered");
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            if (e.getMessage().contains("Duplicate entry") || e.getErrorCode() == 1062) {
                request.setAttribute("error", "Email already registered. Please login or use different email.");
            } else {
                request.setAttribute("error", "Database error: " + e.getMessage());
            }
            
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database driver not found: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            
        } finally {
            DBConnection.closePreparedStatement(pstmt);
            DBConnection.closeConnection(conn);
        }
    }
    
    // Validate input
    private String validateInput(String name, String email, String password, String confirmPassword) {
        
        if (name == null || name.trim().isEmpty()) {
            return "Name is required";
        }
        
        if (email == null || email.trim().isEmpty()) {
            return "Email is required";
        }
        
        if (password == null || password.isEmpty()) {
            return "Password is required";
        }
        
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            return "Please confirm your password";
        }
        
        if (name.trim().length() < 2) {
            return "Name must be at least 2 characters long";
        }
        
        if (name.trim().length() > 50) {
            return "Name cannot exceed 50 characters";
        }
        
        if (!pattern.matcher(email.trim()).matches()) {
            return "Please enter a valid email address";
        }
        
        if (email.trim().length() > 50) {
            return "Email cannot exceed 50 characters";
        }
        
        if (!PasswordUtils.isValidPassword(password)) {
            return "Password must be at least 6 characters long";
        }
        
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match";
        }
        
        return null;
    }
}
