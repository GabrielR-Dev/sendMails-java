package com.sending.mails.services;

import com.sending.mails.services.models.EmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailServices {
    public void sendMail(EmailDTO email) throws MessagingException;
}
