package com.melita.mailservice.service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Data
@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String email, String mailSubject, String emailBody) throws MessagingException {

        MimeMessage mailMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, true);


        mimeMessageHelper.setFrom("melitaltd.group@gmail.com");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(mailSubject);
        mimeMessageHelper.setText(emailBody);

        FileDataSource fileDataSource = new FileDataSource("mail-service/src/main/resources/melitaLogo.png");
        mimeMessageHelper.addAttachment("melitaLogo.png", fileDataSource);


        javaMailSender.send(mailMessage);

    }

}
