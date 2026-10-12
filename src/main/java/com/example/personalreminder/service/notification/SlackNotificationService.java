package com.example.personalreminder.service.notification;

import com.example.personalreminder.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service("slack")
@Slf4j
@RequiredArgsConstructor
public class SlackNotificationService implements NotificationService {
    @Value("${notification.slack.webhook-url}")
    private String webhookUrl;

    private final RestClient.Builder restClientBuilder;

    @Override
    public void send(String to, String message) {
        try {
            restClientBuilder.build()
                    .post()
                    .uri(webhookUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("text", message))
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            log.error("Failed to send slack notification ", e);
        }
    }
}
