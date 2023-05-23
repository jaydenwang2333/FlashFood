package main.util;

import java.util.Random;

/**
 * Randomly generated CAPTCHA tool class
 */
public class ValidateCodeUtils {
    /**
     * Randomly generated verification code
     *
     * @param length length of 4 or 6 digits
     * @return validation code
     */
    public static Integer generateValidateCode(int length) {
        Integer code = null;
        if (length == 4) {
            code = new Random().nextInt(9999);// Generate random numbers up to 9999
            if (code < 1000) {
                code = code + 1000;// Guaranteed 4-digit random number
            }
        } else if (length == 6) {
            code = new Random().nextInt(999999);// Generate random numbers up to 999999
            if (code < 100000) {
                code = code + 100000;// Guaranteed 6-digit random number
            }
        } else {
            throw new RuntimeException("Only 4 or 6 digit verification codes can be generated");
        }
        return code;
    }

    /**
     * Randomly generates a string verification code of specified length
     *
     * @param length length
     * @return captcha
     */
    public static String generateValidateCode4String(int length) {
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        return hash1.substring(0, length);
    }
}
