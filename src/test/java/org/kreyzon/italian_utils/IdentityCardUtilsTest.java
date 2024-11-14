package org.kreyzon.italian_utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

/**
 * Test class for the IdentityCardUtils utility class.
 * <p>
 * This class provides unit tests to verify the functionality of the comprehensive validation methods
 * in {@link IdentityCardUtils} for Italian Electronic Identity Card (CIE) data.
 * </p>
 *
 * Author: Lorenzo Orlando - orlandolorenzo@kreyzon.com
 * Since: 2024-11-13
 */
public class IdentityCardUtilsTest {

    private static final String SERIAL_NUMBER_ONE = "AB12345CD";

    private static final String SERIAL_NUMBER_TWO = "A123456CD";

    /**
     * Tests that the private constructor throws an IllegalStateException.
     */
    @Test
    public void testPrivateConstructor() {
        assertThrows(Exception.class, () -> {
            // Use reflection to access the private constructor
            java.lang.reflect.Constructor<IdentityCardUtils> constructor = IdentityCardUtils.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        }, "Expected IllegalStateException to be thrown when instantiating AnagraficaUtils");
    }


    /**
     * Tests the comprehensive validation of a valid CIE, including correct serial number format
     * and logically consistent issue and expiration dates.
     */
    @Test
    public void testValidCIE() {
        LocalDate issueDate = LocalDate.of(2022, 1, 1);
        LocalDate expirationDate = LocalDate.of(2032, 1, 1);

        assertTrue(IdentityCardUtils.isValidCIE(SERIAL_NUMBER_ONE, issueDate, expirationDate),
                "Expected the CIE details to be valid.");
    }

    /**
     * Tests the validation of a CIE with an invalid serial number format.
     * The serial number does not match the required pattern, so the validation should fail.
     */
    @Test
    public void testInvalidCIESerialNumber() {
        LocalDate issueDate = LocalDate.of(2022, 1, 1);
        LocalDate expirationDate = LocalDate.of(2032, 1, 1);

        assertFalse(IdentityCardUtils.isValidCIE(SERIAL_NUMBER_TWO, issueDate, expirationDate),
                "Expected the CIE to be invalid due to serial number format.");
    }

    /**
     * Tests the validation of a CIE with an issue date that is after the expiration date.
     * This is an invalid scenario, so the validation should fail.
     */
    @Test
    public void testInvalidCIEIssueDateAfterExpiration() {
        LocalDate issueDate = LocalDate.of(2033, 1, 1); // Issue date after expiration
        LocalDate expirationDate = LocalDate.of(2032, 1, 1);

        assertFalse(IdentityCardUtils.isValidCIE(SERIAL_NUMBER_ONE, issueDate, expirationDate),
                "Expected the CIE to be invalid due to issue date after expiration date.");
    }

    /**
     * Tests the validation of a CIE with null issue or expiration dates.
     * Since both dates are required, the validation should fail if either is null.
     */
    @Test
    public void testInvalidCIENullDates() {
        assertFalse(IdentityCardUtils.isValidCIE(SERIAL_NUMBER_ONE, null, LocalDate.of(2032, 1, 1)),
                "Expected the CIE to be invalid due to null issue date.");

        assertFalse(IdentityCardUtils.isValidCIE(SERIAL_NUMBER_ONE, LocalDate.of(2022, 1, 1), null),
                "Expected the CIE to be invalid due to null expiration date.");
    }
}
