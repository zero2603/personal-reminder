package com.example.personalreminder.service.notification;

import com.example.personalreminder.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;

import java.util.List;

@Service("email")
@Slf4j
public class EmailNotificationService implements NotificationService {
    @Value("${notification.smtp.token}")
    private String token;

    @Value("${notification.smtp.sender}")
    private String senderEmail;

    @Override
    public void send(String to, String message) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(token)
                .build();

        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        final MailtrapMail mail = MailtrapMail.builder()
                .from(new Address(senderEmail))
                .to(List.of(new Address(to)))
                .subject("Hello from Mailtrap Sending!")
                .text(message)
                .build();
        // Send an email using Mailtrap Sending API
        try {
            client.send(mail);
            log.info("Email notification sent successfully");
        } catch (Exception e) {
            log.error("Failed to send email notification via Mailtrap", e);
        }
    }
}
