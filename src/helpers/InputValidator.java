package helpers;

public class InputValidator {

    public static boolean isString(String input) {
        return input.matches("[a-zA-Z]+");
    }

    public static boolean isPhoneNumber(String input) {
        return input.matches("[0-9]+") && input.length() == 10;
    }

    public static boolean isValidStringLength(String input,int min, int max) {
        if (input.length() >= min && input.length() <= max) {
            return true;
        }
        return false;
    }

}
