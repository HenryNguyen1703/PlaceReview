package vn.ngochieu.com.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendOtpEmail(String to, String subject, String otp, int expireMinutes) throws MessagingException;
}
