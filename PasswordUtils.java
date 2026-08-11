package com.login.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    
    // Private constructor
    private PasswordUtils() {
    }
    
    // Hash password using SHA-256
    public static String hashPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        try {
            // Get SHA-256 MessageDigest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Convert password to bytes and generate hash
            byte[] encodedHash = digest.digest(
                password.getBytes(java.nio.charset.StandardCharsets.UTF_8)
            );
            
            // Convert byte array to hexadecimal string
            return bytesToHex(encodedHash);
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
    
    // Convert byte array to hex string
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
    
    // Verify password
    public static boolean verifyPassword(String plainPassword, String storedHash) {
        if (plainPassword == null || storedHash == null) {
            return false;
        }
        String hashedInput = hashPassword(plainPassword);
        return hashedInput.equals(storedHash);
    }
    
    // Check if password is valid
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
