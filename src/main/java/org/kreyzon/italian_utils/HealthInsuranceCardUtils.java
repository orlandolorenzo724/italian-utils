package org.kreyzon.italian_utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Utility class for validating Italian Health Insurance Card (Tessera Sanitaria) details.
 * <p>
 * This class provides methods to validate the structure of the Health Insurance Card's serial number
 * and to check the validity of the card based on its expiration date.
 * </p>
 *
 * Author: Lorenzo Orlando - orlandolorenzo@kreyzon.com
 * Since: 2024-11-13
 */
public class HealthInsuranceCardUtils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private HealthInsuranceCardUtils() {
        throw new IllegalStateException("Utility class");
    }

    // Regular expression pattern to match the Health Insurance Card serial number format: 20 numeric digits
    private static final Pattern HIC_SERIAL_PATTERN = Pattern.compile("^\\d{20}$");

    /**
     * Validates the Health Insurance Card serial number format.
     *
     * @param serialNumber the Health Insurance Card serial number to validate
     * @return {@code true} if the serial number is valid, {@code false} otherwise
     */
    public static boolean isValidHICSerial(String serialNumber) {
        if (serialNumber == null) {
            return false;
        }
        return HIC_SERIAL_PATTERN.matcher(serialNumber.toUpperCase()).matches();
    }

    /**
     * Checks if the Health Insurance Card is currently valid based on its expiration date.
     *
     * @param expirationDate the expiration date of the Health Insurance Card
     * @return {@code true} if the card is currently valid, {@code false} otherwise
     */
    public static boolean isCardCurrentlyValid(LocalDate expirationDate) {
        if (expirationDate == null) {
            return false;
        }
        return LocalDate.now().isBefore(expirationDate);
    }

    /**
     * Comprehensive validation for Health Insurance Card details including serial number and expiration date.
     *
     * @param serialNumber   the serial number of the Health Insurance Card
     * @param expirationDate the expiration date of the Health Insurance Card
     * @return {@code true} if all provided Health Insurance Card details are valid, {@code false} otherwise
     */
    public static boolean isValidHealthInsuranceCard(String serialNumber, LocalDate expirationDate) {
        return isValidHICSerial(serialNumber) && isCardCurrentlyValid(expirationDate);
    }
}
