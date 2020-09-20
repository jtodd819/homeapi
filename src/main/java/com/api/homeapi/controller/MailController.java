package com.api.homeapi.controller;
import com.api.homeapi.model.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/mail")
public class MailController {

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping()
    public ResponseEntity<Void> sendMail(@RequestBody Mail mail) {
        // Send Mail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo("me@james-todd.net");
        message.setSubject("Home Site Notification from " + mail.getFrom());
        message.setText(mail.getSubject() + ":\n" + mail.getBody());
        emailSender.send(message);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
