package org.kreyzon.italian_utils;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the AnagraficaUtils utility class.
 * <p>
 * This class provides unit tests to verify the functionality of the validation and formatting methods
 * in {@link AnagraficaUtils} for Italian names, surnames, titles, and other personal information.
 * </p>
 *
 * Author: Lorenzo Orlando - orlandolorenzo@kreyzon.com
 * Since: 2024-11-12
 */
public class AnagraficaUtilsTest {

    /**
     * Tests that valid names are correctly recognized.
     * Valid names contain only letters and may have multiple parts (e.g., first and middle names).
     */
    @Test
    public void testValidName() {
        assertTrue(AnagraficaUtils.isValidName("Mario"));
        assertTrue(AnagraficaUtils.isValidName("Anna Maria"));
    }

    /**
     * Tests that invalid names are correctly recognized.
     * Invalid names include those with special characters or empty strings.
     */
    @Test
    public void testInvalidName() {
        assertFalse(AnagraficaUtils.isValidName("M@rio"));
        assertFalse(AnagraficaUtils.isValidName(""));
    }

    /**
     * Tests that valid surnames are correctly recognized.
     * Valid surnames contain only letters.
     */
    @Test
    public void testValidSurname() {
        assertTrue(AnagraficaUtils.isValidSurname("Rossi"));
        assertTrue(AnagraficaUtils.isValidSurname("Verdi"));
    }

    /**
     * Tests that invalid surnames are correctly recognized.
     * Invalid surnames include those with special characters or empty strings.
     */
    @Test
    public void testInvalidSurname() {
        assertFalse(AnagraficaUtils.isValidSurname("Ros$si"));
        assertFalse(AnagraficaUtils.isValidSurname(""));
    }

    /**
     * Tests that valid Italian honorific titles are correctly recognized.
     * Valid titles include "Sig.", "Sig.ra", and "Dott.".
     */
    @Test
    public void testValidTitle() {
        assertTrue(AnagraficaUtils.isValidTitle("Sig."));
        assertTrue(AnagraficaUtils.isValidTitle("Sig.ra"));
        assertTrue(AnagraficaUtils.isValidTitle("Dott."));
    }

    /**
     * Tests that invalid titles are correctly rejected.
     * Invalid titles include unrecognized titles like "Mr." and "Mrs".
     */
    @Test
    public void testInvalidTitle() {
        assertFalse(AnagraficaUtils.isValidTitle("Mr."));
        assertFalse(AnagraficaUtils.isValidTitle("Mrs"));
        assertFalse(AnagraficaUtils.isValidTitle(""));
    }

    /**
     * Tests that valid gender values ("M" and "F") are correctly recognized.
     */
    @Test
    public void testValidGender() {
        assertTrue(AnagraficaUtils.isValidGender("M"));
        assertTrue(AnagraficaUtils.isValidGender("F"));
        assertTrue(AnagraficaUtils.isValidGender("m"));
        assertTrue(AnagraficaUtils.isValidGender("f"));
    }

    /**
     * Tests that invalid gender values are correctly rejected.
     * Invalid values include anything other than "M" or "F".
     */
    @Test
    public void testInvalidGender() {
        assertFalse(AnagraficaUtils.isValidGender("X"));
        assertFalse(AnagraficaUtils.isValidGender("Male"));
    }

    /**
     * Tests age calculation based on the provided birthdate.
     * Assumes a static birthdate and compares against the expected age.
     */
    @Test
    public void testCalculateAge() {
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        int age = AnagraficaUtils.calculateAge(birthdate);
        assertEquals(34, age);  // Adjust the expected age based on the current date
    }

    /**
     * Tests the formatting of a full name with title, name, and surname.
     * Checks both cases with and without an optional title.
     */
    @Test
    public void testFormatFullName() {
        assertEquals("Sig. Mario Rossi", AnagraficaUtils.formatFullName("Sig.", "Mario", "Rossi"));
        assertEquals("Anna Verdi", AnagraficaUtils.formatFullName(null, "Anna", "Verdi"));
    }

    /**
     * Tests that initials are correctly extracted from the name and surname.
     */
    @Test
    public void testGetInitials() {
        assertEquals("MR", AnagraficaUtils.getInitials("Mario", "Rossi"));
        assertEquals("AV", AnagraficaUtils.getInitials("Anna", "Verdi"));
    }

    /**
     * Tests that a name or surname is normalized correctly.
     * Only the first letter is capitalized, and the rest are made lowercase.
     */
    @Test
    public void testNormalizeName() {
        assertEquals("Mario", AnagraficaUtils.normalizeName("mario"));
        assertEquals("Anna", AnagraficaUtils.normalizeName("ANNA"));
    }

    /**
     * Tests that the name length validation works as expected.
     * Ensures names fall within specified minimum and maximum lengths.
     */
    @Test
    public void testNameLengthValid() {
        assertTrue(AnagraficaUtils.isNameLengthValid("Mario", 2, 10));
        assertFalse(AnagraficaUtils.isNameLengthValid("M", 2, 10));
        assertFalse(AnagraficaUtils.isNameLengthValid("Mariorossigiuseppe", 2, 10));
    }

    /**
     * Tests that the age is over 18 based on the provided birthdate.
     * Assumes a static birthdate and compares against the expected age.
     */
    @Test
    public void testIsOver18() {
        LocalDate birthdate = LocalDate.of(2004, 1, 1);
        assertTrue(AnagraficaUtils.isOver18(birthdate));
    }

   @Test
   public void testIsUnder18() {
       LocalDate birthdate = LocalDate.of(2010, 1, 1);
       assertFalse(AnagraficaUtils.isOver18(birthdate));
   }
}