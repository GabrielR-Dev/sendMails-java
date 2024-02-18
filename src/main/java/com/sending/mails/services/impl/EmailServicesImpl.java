package com.sending.mails.services.impl;

import com.sending.mails.services.IEmailServices;
import com.sending.mails.services.models.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServicesImpl implements IEmailServices {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailServicesImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendMail(EmailDTO email) throws MessagingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");

            helper.setTo(email.getDestinatario());
            helper.setSubject(email.getAsunto());

            Context context = new Context();
            context.setVariable("mensaje", "Esto es una prueba... 2");
            String contextHTML = templateEngine.process("email", context);

            helper.setText(contextHTML,true);
            javaMailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException("Error al enviar correo: "+e.getMessage(),e);
        }

    }

}
