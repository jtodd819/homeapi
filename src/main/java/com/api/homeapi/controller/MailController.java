package com.api.homeapi.controller;
import com.api.homeapi.model.Mail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MailController {

    @GetMapping("/mail")
    public Mail mail(@RequestParam(value="to") String to, @RequestParam(value= "from") String from,
    @RequestParam(value="subject") String subject, @RequestParam(value="body") String body) {
        return new Mail(to, from, subject, body);
    }
}
