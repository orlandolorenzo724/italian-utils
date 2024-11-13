package org.kreyzon.italian_utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the BankingUtils utility class.
 * <p>
 * This class provides unit tests to verify the functionality of the validation and formatting methods
 * in {@link BankingUtils} for IBAN and SWIFT/BIC codes.
 * </p>
 *
 * Author: Lorenzo Orlando - orlandolorenzo@kreyzon.com
 * Since: 2024-11-12
 */
public class BankingUtilsTest {

    /**
     * Tests the validation of a valid IBAN.
     */
    @Test
    public void testValidIBAN() {
        String validIban = "IT60X0542811101000000123456"; // Example valid Italian IBAN
        assertTrue(BankingUtils.isValidIBAN(validIban), "Expected the IBAN to be valid.");
    }

    /**
     * Tests the validation of an invalid IBAN with incorrect check digits.
     */
    @Test
    public void testInvalidIBANCheckDigit() {
        String invalidIban = "IT60X0542811101000000123450"; // Incorrect check digit
        assertFalse(BankingUtils.isValidIBAN(invalidIban), "Expected the IBAN to be invalid due to incorrect check digits.");
    }

    /**
     * Tests the validation of an IBAN with incorrect length.
     */
    @Test
    public void testInvalidIBANLength() {
        String invalidIban = "IT60X05428111010000001234"; // Too short
        assertFalse(BankingUtils.isValidIBAN(invalidIban), "Expected the IBAN to be invalid due to incorrect length.");
    }

    /**
     * Tests the formatting of a valid IBAN to include spaces every 4 characters.
     */
    @Test
    public void testFormatIBAN() {
        String iban = "IT60X0542811101000000123456";
        String expectedFormattedIban = "IT60 X054 2811 1010 0000 0123 456";
        assertEquals(expectedFormattedIban, BankingUtils.formatIBAN(iban), "Expected the IBAN to be formatted with spaces.");
    }

    /**
     * Tests that formatting an invalid IBAN throws an IllegalArgumentException.
     */
    @Test
    public void testFormatInvalidIBAN() {
        String invalidIban = "IT60X0542811101000000123450"; // Incorrect check digit
        assertThrows(IllegalArgumentException.class, () -> BankingUtils.formatIBAN(invalidIban), "Expected an exception for an invalid IBAN.");
    }

    /**
     * Tests the validation of a valid SWIFT/BIC code with 8 characters.
     */
    @Test
    public void testValidSWIFT8Char() {
        String validSwift = "BCITITMM"; // Example valid 8-character SWIFT
        assertTrue(BankingUtils.isValidSWIFT(validSwift), "Expected the SWIFT/BIC code to be valid.");
    }

    /**
     * Tests the validation of a valid SWIFT/BIC code with 11 characters.
     */
    @Test
    public void testValidSWIFT11Char() {
        String validSwift = "BCITITMMXXX"; // Example valid 11-character SWIFT
        assertTrue(BankingUtils.isValidSWIFT(validSwift), "Expected the SWIFT/BIC code to be valid.");
    }

    /**
     * Tests the validation of an invalid SWIFT/BIC code with incorrect structure.
     */
    @Test
    public void testInvalidSWIFTStructure() {
        String invalidSwift = "BC1TITMM"; // Contains a digit in the bank code
        assertFalse(BankingUtils.isValidSWIFT(invalidSwift), "Expected the SWIFT/BIC code to be invalid due to incorrect structure.");
    }

    /**
     * Tests the validation of a SWIFT/BIC code with incorrect length.
     */
    @Test
    public void testInvalidSWIFTLength() {
        String invalidSwift = "BCITITM"; // Only 7 characters
        assertFalse(BankingUtils.isValidSWIFT(invalidSwift), "Expected the SWIFT/BIC code to be invalid due to incorrect length.");
    }
}