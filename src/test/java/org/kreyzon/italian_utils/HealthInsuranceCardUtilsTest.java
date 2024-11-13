package org.kreyzon.italian_utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

/**
 * Test class for the HealthInsuranceCardUtils utility class.
 * <p>
 * This class provides unit tests to verify the functionality of the validation methods
 * in {@link HealthInsuranceCardUtils} for Italian Health Insurance Card (Tessera Sanitaria) data.
 * </p>
 *
 * Author: Lorenzo Orlando - orlandolorenzo@kreyzon.com
 * Since: 2024-11-13
 */
public class HealthInsuranceCardUtilsTest {

    /**
     * Tests the validation of a valid Health Insurance Card serial number.
     */
    @Test
    public void testValidHICSerial() {
        String validSerial = "00123456789012345678";
        assertTrue(HealthInsuranceCardUtils.isValidHICSerial(validSerial),
                "Expected the serial number to be valid.");
    }

    /**
     * Tests the validation of an invalid Health Insurance Card serial number with incorrect length.
     */
    @Test
    public void testInvalidHICSerial_Length() {
        String invalidSerial = "AB12345678901234567"; // 19 characters
        assertFalse(HealthInsuranceCardUtils.isValidHICSerial(invalidSerial),
                "Expected the serial number to be invalid due to incorrect length.");
    }

    /**
     * Tests the validation of an invalid Health Insurance Card serial number with special characters.
     */
    @Test
    public void testInvalidHICSerial_SpecialCharacters() {
        String invalidSerial = "AB1234567890!@#$%^&*";
        assertFalse(HealthInsuranceCardUtils.isValidHICSerial(invalidSerial),
                "Expected the serial number to be invalid due to special characters.");
    }

    /**
     * Tests the validation of a Health Insurance Card with a future expiration date.
     */
    @Test
    public void testCardCurrentlyValid_FutureDate() {
        LocalDate futureDate = LocalDate.now().plusYears(1);
        assertTrue(HealthInsuranceCardUtils.isCardCurrentlyValid(futureDate),
                "Expected the card to be valid with a future expiration date.");
    }

    /**
     * Tests the validation of a Health Insurance Card with a past expiration date.
     */
    @Test
    public void testCardCurrentlyValid_PastDate() {
        LocalDate pastDate = LocalDate.now().minusYears(1);
        assertFalse(HealthInsuranceCardUtils.isCardCurrentlyValid(pastDate),
                "Expected the card to be invalid with a past expiration date.");
    }

    /**
     * Tests the comprehensive validation of a valid Health Insurance Card.
     */
    @Test
    public void testValidHealthInsuranceCard() {
        String validSerial = "00123456789012345678";
        LocalDate futureDate = LocalDate.now().plusYears(1);
        assertTrue(HealthInsuranceCardUtils.isValidHealthInsuranceCard(validSerial, futureDate),
                "Expected the Health Insurance Card details to be valid.");
    }

    /**
     * Tests the comprehensive validation of an invalid Health Insurance Card due to serial number.
     */
    @Test
    public void testInvalidHealthInsuranceCard_Serial() {
        String invalidSerial = "AB12345678901234567"; // 19 characters
        LocalDate futureDate = LocalDate.now().plusYears(1);
        assertFalse(HealthInsuranceCardUtils.isValidHealthInsuranceCard(invalidSerial, futureDate),
                "Expected the Health Insurance Card to be invalid due to serial number.");
    }

    /**
     * Tests the comprehensive validation of an invalid Health Insurance Card due to expiration date.
     */
    @Test
    public void testInvalidHealthInsuranceCard_ExpirationDate() {
        String validSerial = "AB123456789012345678";
        LocalDate pastDate = LocalDate.now().minusYears(1);
        assertFalse(HealthInsuranceCardUtils.isValidHealthInsuranceCard(validSerial, pastDate),
                "Expected the Health Insurance Card to be invalid due to expiration date.");
    }
}