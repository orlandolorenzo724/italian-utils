package org.kreyzon.italian_utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Utility class for validating and formatting Italian Electronic Identity Card (CIE) serial numbers and related data.
 * <p>
 * This class provides methods to validate the structure of CIE serial numbers and additional details
 * such as issue date, expiration date, and logical consistency between dates.
 * </p>
 *
 * Author: Lorenzo Orlando - orlandolorenzo@kreyzon.com
 * Since: 2024-11-13
 */
public class IdentityCardUtils {

    // Regular expression pattern to match the CIE serial number format: two letters, five digits, two letters
    private static final Pattern CIE_SERIAL_PATTERN = Pattern.compile("^[A-Z]{2}\\d{5}[A-Z]{2}$");

    /**
     * Validates the CIE serial number format.
     *
     * @param serialNumber the CIE serial number to validate
     * @return {@code true} if the serial number is valid, {@code false} otherwise
     */
    public static boolean isValidCIESerial(String serialNumber) {
        if (serialNumber == null) {
            return false;
        }
        return CIE_SERIAL_PATTERN.matcher(serialNumber.toUpperCase()).matches();
    }

    /**
     * Comprehensive validation for CIE details including serial number, issue date, and expiration date.
     *
     * @param serialNumber   the serial number of the CIE
     * @param issueDate      the issue date of the CIE (must be before expiration date)
     * @param expirationDate the expiration date of the CIE (must be after issue date)
     * @return {@code true} if all provided CIE details are valid, {@code false} otherwise
     */
    public static boolean isValidCIE(String serialNumber, LocalDate issueDate, LocalDate expirationDate) {
        // Validate serial number format
        if (!isValidCIESerial(serialNumber)) {
            return false;
        }

        // Validate that issueDate and expirationDate are not null and have logical order
        if (issueDate == null || expirationDate == null) {
            return false;
        }
        if (!issueDate.isBefore(expirationDate)) {
            return false;
        }

        // Additional checks can be added as needed
        return true;
    }
}