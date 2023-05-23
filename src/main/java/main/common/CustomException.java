package main.common;


public class CustomException extends RuntimeException {
    /**
     * This exception will be caught by {@link GlobalExceptionHandler} and returned to the front-end.
     *
     * @param message Exception message
     */
    public CustomException(String message) {
        super(message);
    }

}
