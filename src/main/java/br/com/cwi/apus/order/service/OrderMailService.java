package br.com.cwi.apus.order.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class OrderMailService {

    private JavaMailSender mailSender;

    public void send(String orderId, String emailTo) {

        try {

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(emailTo);
            email.setSubject("Pedido " + orderId);
            email.setText("Seu pedido " + orderId + " foi criado com sucesso");

            mailSender.send(email);

            log.info("email enviado com sucesso");

        } catch (Exception e) {
            log.error("Falha ao tentar enviar email", e);
        }
    }
}
