package br.com.project.screenmatch.service;

import br.com.project.screenmatch.domain.entity.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(Serie serie, String recipientEmail, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject("Você recebeu uma série compartilhada: " + serie.getTitle());
        simpleMailMessage.setText("Você recebeu uma série compartilhada: " + serie.getTitle() + "\n\n" +
                "Mensagem: " + message);
        javaMailSender.send(simpleMailMessage);
    }
}
