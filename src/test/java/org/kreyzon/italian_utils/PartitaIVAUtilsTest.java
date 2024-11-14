package org.kreyzon.italian_utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the PartitaIVAUtils utility class.
 * <p>
 * This class provides unit tests to verify the functionality of the validation
 * and formatting methods in {@link PartitaIVAUtils} for Italian VAT numbers (Partita IVA).
 * </p>
 *
 * Author: Lorenzo Orlando - orlandolorenzo@kreyzon.com
 * Since: 2024-11-12
 */
public class PartitaIVAUtilsTest {

    private static final String VALID_PARTITA_IVA = "IT12345678903";

    /**
     * Tests that the private constructor throws an IllegalStateException.
     */
    @Test
    public void testPrivateConstructor() {
        assertThrows(Exception.class, () -> {
            // Use reflection to access the private constructor
            java.lang.reflect.Constructor<PartitaIVAUtils> constructor = PartitaIVAUtils.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        }, "Expected IllegalStateException to be thrown when instantiating AnagraficaUtils");
    }


    /**
     * Tests the formatting of a valid Partita IVA without the "IT" prefix.
     */
    @Test
    public void testFormatPartitaIVAWithoutPrefix() {
        String partitaIVA = "12345678903";
        assertEquals(VALID_PARTITA_IVA, PartitaIVAUtils.formatPartitaIVA(partitaIVA),
                "Expected the formatted Partita IVA to include the 'IT' prefix.");
    }

    /**
     * Tests the formatting of a valid Partita IVA with the "IT" prefix.
     */
    @Test
    public void testFormatPartitaIVAWithPrefix() {
        assertEquals(VALID_PARTITA_IVA, PartitaIVAUtils.formatPartitaIVA(VALID_PARTITA_IVA),
                "Expected the formatted Partita IVA to retain the 'IT' prefix.");
    }

    /**
     * Tests the formatting of an invalid Partita IVA with incorrect length.
     */
    @Test
    public void testFormatInvalidPartitaIVAWithIncorrectLength() {
        String invalidPartitaIVA = "1234567890"; // Only 10 digits
        assertThrows(IllegalArgumentException.class,
                () -> PartitaIVAUtils.formatPartitaIVA(invalidPartitaIVA),
                "Expected an IllegalArgumentException due to incorrect length.");
    }

    /**
     * Tests the formatting of an invalid Partita IVA with non-digit characters.
     */
    @Test
    public void testFormatInvalidPartitaIVANonDigit() {
        String invalidPartitaIVA = "12345A78903"; // Contains a letter
        assertThrows(IllegalArgumentException.class,
                () -> PartitaIVAUtils.formatPartitaIVA(invalidPartitaIVA),
                "Expected an IllegalArgumentException due to non-digit characters.");
    }

    /**
     * Tests the formatting of a null Partita IVA.
     */
    @Test
    public void testFormatNullPartitaIVA() {
        assertThrows(IllegalArgumentException.class,
                () -> PartitaIVAUtils.formatPartitaIVA(null),
                "Expected an IllegalArgumentException due to null input.");
    }
}