public class MultipleLettersException extends Exception {
    @Override public String getMessage() {
        String message = "More than one letter was entered, please only enter one letter";
        return message;
    }

}
