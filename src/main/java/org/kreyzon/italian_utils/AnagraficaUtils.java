package org.kreyzon.italian_utils;

import java.time.LocalDate;
import java.time.Period;

/**
 * Utility class for validating Italian personal data such as names and surnames.
 * <p>
 * This class provides validation and formatting methods to handle Italian naming
 * conventions and personal information like titles, genders, and ages.
 * </p>
 *
 * Author: Lorenzo Orlando - orlandolorenzo@kreyzon.com
 * Since: 2024-11-12
 */
public class AnagraficaUtils {

    /**
     * Validates a name, allowing only letters and spaces.
     * Useful for handling cases with middle names or multi-part first names.
     *
     * @param name the name to validate
     * @return {@code true} if the name contains only letters and spaces, {@code false} otherwise
     */
    public static boolean isValidName(String name) {
        return name != null && name.matches("[A-Za-z ]+");
    }

    /**
     * Validates a surname, allowing only letters. Does not allow spaces, assuming a single surname.
     *
     * @param surname the surname to validate
     * @return {@code true} if the surname contains only letters, {@code false} otherwise
     */
    public static boolean isValidSurname(String surname) {
        return surname != null && surname.matches("[A-Za-z]+");
    }

    /**
     * Validates an Italian honorific title (e.g., Sig., Sig.ra, Dott.).
     *
     * @param title the title to validate
     * @return {@code true} if the title is a recognized Italian honorific, {@code false} otherwise
     */
    public static boolean isValidTitle(String title) {
        return title != null && title.matches("(Sig\\.|Sig\\.ra|Dott\\.)");
    }

    /**
     * Validates gender based on Italian standards (M for male, F for female).
     *
     * @param gender the gender to validate
     * @return {@code true} if gender is "M" or "F", {@code false} otherwise
     */
    public static boolean isValidGender(String gender) {
        return "M".equalsIgnoreCase(gender) || "F".equalsIgnoreCase(gender);
    }

    /**
     * Calculates age based on the provided birthdate.
     *
     * @param birthdate the birthdate in LocalDate format
     * @return the calculated age as an integer
     */
    public static int calculateAge(LocalDate birthdate) {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    /**
     * Formats a full name with title, name, and surname in proper Italian order.
     *
     * @param title the title (optional, can be null)
     * @param name the name
     * @param surname the surname
     * @return the formatted full name
     */
    public static String formatFullName(String title, String name, String surname) {
        String fullName = (title != null ? title + " " : "") + name + " " + surname;
        return fullName.trim();
    }

    /**
     * Extracts initials from the provided name and surname.
     *
     * @param name the name
     * @param surname the surname
     * @return the initials in uppercase
     */
    public static String getInitials(String name, String surname) {
        String nameInitial = name != null && !name.isEmpty() ? name.substring(0, 1).toUpperCase() : "";
        String surnameInitial = surname != null && !surname.isEmpty() ? surname.substring(0, 1).toUpperCase() : "";
        return nameInitial + surnameInitial;
    }

    /**
     * Normalizes the name or surname by capitalizing the first letter and making the rest lowercase.
     *
     * @param input the name or surname to normalize
     * @return the normalized name or surname
     */
    public static String normalizeName(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Validates that the name meets minimum and maximum length requirements.
     *
     * @param name the name to validate
     * @param minLength minimum length for the name
     * @param maxLength maximum length for the name
     * @return {@code true} if the name is within the specified length range, {@code false} otherwise
     */
    public static boolean isNameLengthValid(String name, int minLength, int maxLength) {
        return name != null && name.length() >= minLength && name.length() <= maxLength;
    }

    /**
     * Validates that the age is over 18 based on the provided birthdate.
     *
     * @param birthdate the birthdate in LocalDate format
     * @return {@code true} if the age is 18 or over, {@code false} otherwise
     */
    public static boolean isOver18(LocalDate birthdate) {
        return Period.between(birthdate, LocalDate.now()).getYears() >= 18;
    }
}