package org.kreyzon.italian_utils;

import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * Utility class for validating and formatting banking information such as IBAN and SWIFT codes.
 * <p>
 * Author: Lorenzo Orlando - orlandolorenzo@kreyzon.com
 * Since: 2024-11-13
 */
public class BankingUtils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private BankingUtils() {
        throw new IllegalStateException("Utility class");
    }

    // Regular expression patterns for IBAN and SWIFT/BIC codes
    private static final Pattern IBAN_PATTERN = Pattern.compile("^[A-Z]{2}\\d{2}[A-Z0-9]{1,30}$");
    private static final Pattern SWIFT_PATTERN = Pattern.compile("^[A-Z]{4}[A-Z]{2}[A-Z0-9]{2}([A-Z0-9]{3})?$");

    /**
     * Validates an IBAN based on length, structure, and checksum.
     *
     * @param iban the IBAN to validate
     * @return {@code true} if the IBAN is valid, {@code false} otherwise
     */
    public static boolean isValidIBAN(String iban) {
        if (iban == null || !IBAN_PATTERN.matcher(iban).matches()) {
            return false;
        }

        // Move the first four characters to the end and replace letters with numbers
        String reformattedIBAN = iban.substring(4) + iban.substring(0, 4);
        StringBuilder numericIBAN = new StringBuilder();

        for (char c : reformattedIBAN.toCharArray()) {
            if (Character.isDigit(c)) {
                numericIBAN.append(c);
            } else {
                numericIBAN.append(Character.getNumericValue(c));
            }
        }

        // Validate with modulo 97
        BigInteger ibanNumber = new BigInteger(numericIBAN.toString());
        return ibanNumber.mod(BigInteger.valueOf(97)).intValue() == 1;
    }

    /**
     * Validates a SWIFT/BIC code based on structure and length.
     *
     * @param swift the SWIFT/BIC code to validate
     * @return {@code true} if the SWIFT/BIC code is valid, {@code false} otherwise
     */
    public static boolean isValidSWIFT(String swift) {
        return swift != null && SWIFT_PATTERN.matcher(swift).matches();
    }

    /**
     * Formats an IBAN for readability by grouping it in blocks of 4 characters.
     *
     * @param iban the IBAN to format
     * @return the formatted IBAN with spaces every 4 characters
     */
    public static String formatIBAN(String iban) {
        if (!isValidIBAN(iban)) {
            throw new IllegalArgumentException("Invalid IBAN");
        }

        return iban.replaceAll(".{4}(?!$)", "$0 ");
    }
}
