package helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidator {

    public static boolean isString(String input) {
        return input.matches("[a-zA-Z]+");
    }

    public static boolean isPhoneNumber(String input) {
        return input.matches("[0-9]+") && input.length() == 10;
    }

    public static boolean isValidStringLength(String input, int min, int max) {
        if (input.length() >= min && input.length() <= max) {
            return true;
        }
        return false;
    }

    public static boolean isDate(String input) {
        if (!input.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }

        try {
            LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean dateRange(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return (!start.isBefore(LocalDate.now()) && start.isBefore(end));
    }
}
