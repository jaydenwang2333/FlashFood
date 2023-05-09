package main.common;

public class CustomException {
    /**
     * This expection will catch by GlobalExceptionHandler, then return to frontend
     *
     *
     * @param message expection message
     */
    public CustomException(String message) {
        super(message);
    }

}
