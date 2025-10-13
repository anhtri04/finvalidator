/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hydrangea.finvalidator.utils;

/**
 *
 * @author Anh Tri
 */

 /**
 * Luhn algorithm utility for card number validation.
 */

public final class Luhn {
    private Luhn() {}
    
    /**
     * Return true if the numeric string passes Luhn check.
     * Accepts spaces inside string (they are ignored).
     */
    public static boolean check(String digits) {
        if (digits == null) return false;
        String s = digits.replaceAll("\\s+", "");
        if (s.isEmpty()) return false;
        int sum = 0;
        boolean alternate = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) return false;
            int n = c - '0';
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }
}
