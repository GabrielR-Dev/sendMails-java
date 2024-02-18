package com.sending.mails.controllers;

import com.sending.mails.services.IEmailServices;
import com.sending.mails.services.models.EmailDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EmailController {

    @Autowired
    IEmailServices iEmailServices;

    @PostMapping("/sendEmail")
    private ResponseEntity<String> sendEmail(@RequestBody EmailDTO email) throws MessagingException {
        iEmailServices.sendMail(email);
        return new ResponseEntity<>("Correo enviado exitosamente.", HttpStatus.OK);
    }

}
