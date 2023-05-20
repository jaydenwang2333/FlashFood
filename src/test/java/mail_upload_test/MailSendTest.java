package mail_upload_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;

@Controller
public class MailSendTest {
    @Autowired
    JavaMailSender javaMailSender;

    @Test
    public void sendMail() {
        javaMailSender = new JavaMailSenderImpl();

        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("no-reply-me@foxmail.com");
        simpleMailMessage.setSubject("Test");
        simpleMailMessage.setTo("1105799454@qq.com");
        simpleMailMessage.setText("test111");
        javaMailSender.send(simpleMailMessage);
    }
}
