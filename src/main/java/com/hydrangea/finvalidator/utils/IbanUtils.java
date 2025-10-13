/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hydrangea.finvalidator.utils;

import java.math.BigInteger;

/**
 *
 * @author Anh Tri
 */

 /**
 * Minimal IBAN validation util using the standard mod-97 check.
 * Note: does not validate country-specific length or BBAN format beyond the generic rules.
 */

public final class IbanUtils {
    private IbanUtils() {}

    /**
     * Validate IBAN using rearrange + A=10 conversion + mod 97 == 1.
     * Returns false for null/invalid input.
     */
    public static boolean validateIban(String iban) {
        if (iban == null) return false;
        String s = iban.replaceAll("\\s+", "").toUpperCase();
        if (s.length() < 15 || s.length() > 34) return false;

        String head = s.substring(0, 4);
        String tail = s.substring(4);
        String rearranged = tail + head;

        StringBuilder numeric = new StringBuilder(rearranged.length() * 2);
        for (char c : rearranged.toCharArray()) {
            if (Character.isDigit(c)) {
                numeric.append(c);
            } else if (Character.isLetter(c)) {
                int val = Character.getNumericValue(c); // A -> 10 ...
                if (val < 10) return false; // invalid letter mapping
                numeric.append(val);
            } else {
                return false;
            }
        }

        // BigInteger from numeric representation
        try {
            BigInteger big = new BigInteger(numeric.toString());
            return big.mod(BigInteger.valueOf(97)).intValue() == 1;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
