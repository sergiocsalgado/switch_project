package org.switch2022.project.utils;

/**
 * The {@code StringValidation} class provides utility methods for validating strings.
 * It ensures that the provided strings are not null or empty.
 */
public final class StringValidation {
    /**
     * Private constructor to prevent instantiation of the class.
     */
    private StringValidation() {}

    /**
     * Checks if the given string is null.
     *
     * @param parameter    the name of the parameter being checked
     * @param stringToCheck the string to check for null
     * @throws IllegalArgumentException if the stringToCheck is null
     */
    public static void checkNull(String parameter, String stringToCheck) {
        if(stringToCheck == null) {
            throw new IllegalArgumentException(parameter + " cannot be null");
        }
    }

    /**
     * Checks if the given string is blank (empty or contains only whitespace characters).
     *
     * @param parameter    the name of the parameter being checked
     * @param stringToCheck the string to check for blank
     * @throws IllegalArgumentException if the stringToCheck is blank
     */
    public static void checkBlank(String parameter, String stringToCheck) {
        if(stringToCheck.isBlank()) {
            throw new IllegalArgumentException(parameter + " cannot be empty");
        }
    }
}
