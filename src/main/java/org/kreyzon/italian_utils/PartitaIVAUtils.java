package org.kreyzon.italian_utils;

import java.util.regex.Pattern;

/**
 * Utility class for validating and formatting Italian VAT numbers (Partita IVA).
 * <p>
 * This class provides methods to validate the structure and check digit of
 * Italian VAT numbers, ensuring they conform to the official standards.
 * </p>
 *
 * Author: Lorenzo Orlando - orlandolorenzo@kreyzon.com
 * Since: 2024-11-12
 */
public class PartitaIVAUtils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private PartitaIVAUtils() {
        throw new IllegalStateException("Utility class");
    }

    // Regular expression pattern to match exactly 11 digits
    private static final Pattern PARTITA_IVA_PATTERN = Pattern.compile("\\d{11}");

    /**
     * Validates the given Partita IVA (Italian VAT number).
     *
     * @param partitaIVA the Partita IVA to validate
     * @return {@code true} if the Partita IVA is valid, {@code false} otherwise
     */
    public static boolean isValidPartitaIVA(String partitaIVA) {
        if (partitaIVA == null) {
            return false;
        }

        // Remove "IT" prefix if present
        if (partitaIVA.startsWith("IT")) {
            partitaIVA = partitaIVA.substring(2);
        }

        // Check if the remaining part matches the 11-digit pattern
        if (!PARTITA_IVA_PATTERN.matcher(partitaIVA).matches()) {
            return false;
        }

        // Validate the check digit
        return isValidCheckDigit(partitaIVA);
    }

    /**
     * Checks if the check digit of the Partita IVA is valid using the Luhn algorithm.
     *
     * @param partitaIVA the normalized Partita IVA to check (11 digits)
     * @return {@code true} if the check digit is valid, {@code false} otherwise
     */
    private static boolean isValidCheckDigit(String partitaIVA) {
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            int digit = Character.getNumericValue(partitaIVA.charAt(i));
            if ((i + 1) % 2 == 0) { // Even position (1-based index)
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return sum % 10 == 0;
    }

    /**
     * Formats the given Partita IVA by adding the "IT" country prefix.
     *
     * @param partitaIVA the Partita IVA to format
     * @return the formatted Partita IVA with the "IT" prefix
     */
    public static String formatPartitaIVA(String partitaIVA) {
        if (partitaIVA == null) {
            throw new IllegalArgumentException("Partita IVA cannot be null");
        }

        // Remove existing "IT" prefix if present
        if (partitaIVA.startsWith("IT")) {
            partitaIVA = partitaIVA.substring(2);
        }

        // Validate the remaining part
        if (!PARTITA_IVA_PATTERN.matcher(partitaIVA).matches()) {
            throw new IllegalArgumentException("Invalid Partita IVA");
        }

        return "IT" + partitaIVA;
    }
}