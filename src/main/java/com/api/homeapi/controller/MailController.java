package com.api.homeapi.controller;
import com.api.homeapi.model.Mail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/mail")
public class MailController {

    @PostMapping()
    public ResponseEntity<Void> sendMail(@RequestBody Mail mail) {
        // Send Mail
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}
