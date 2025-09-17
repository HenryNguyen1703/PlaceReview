package vn.ngochieu.com.service.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import vn.ngochieu.com.service.EmailService;
import org.thymeleaf.context.Context;
import jakarta.mail.MessagingException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {

    JavaMailSender mailSender;

    TemplateEngine templateEngine;

    @Override
    public void sendOtpEmail(String to, String username, String otp, int expireMinutes) throws MessagingException {

        Context context = new Context();
        context.setVariable("username", to);
        context.setVariable("otp", otp);
        context.setVariable("expiryMinutes", expireMinutes);

        // Render HTML từ template
        String htmlContent = templateEngine.process("email/otp-email", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("henrynguyen24317@gmail.com");
        helper.setTo(to);
        helper.setSubject("Mã OTP xác thực tài khoản");
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
