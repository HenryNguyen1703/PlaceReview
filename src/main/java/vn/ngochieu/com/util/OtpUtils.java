package vn.ngochieu.com.util;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.security.SecureRandom;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpUtils {
    static SecureRandom random = new SecureRandom();

    public static String generateOtp(int length) {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
