package com.example.personalreminder.service;

import com.example.personalreminder.service.notification.EmailNotificationService;
import com.example.personalreminder.service.notification.SlackNotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.restclient.autoconfigure.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotificationServiceFactoryTests {

    @Test
    void resolvesRegisteredNotificationServicesByType() {
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(RestClientAutoConfiguration.class))
                .withPropertyValues(
                        "notification.slack.webhook-url=https://example.invalid/slack",
                        "notification.smtp.token=test-token",
                        "notification.smtp.sender=test@example.invalid")
                .withUserConfiguration(
                        NotificationServiceFactory.class,
                        EmailNotificationService.class,
                        SlackNotificationService.class)
                .run(context -> {
                    assertThat(context).hasNotFailed().hasSingleBean(RestClient.Builder.class);
                    var factory = context.getBean(NotificationServiceFactory.class);

                    assertSame(context.getBean(EmailNotificationService.class), factory.get("email"));
                    assertSame(context.getBean(SlackNotificationService.class), factory.get("slack"));
                    assertThrows(IllegalArgumentException.class, () -> factory.get("sms"));
                    assertThrows(IllegalArgumentException.class, () -> factory.get(null));
                    assertThrows(IllegalArgumentException.class, () -> factory.get(" "));
                });
    }
}
