package com.example.personalreminder.cron;

import com.example.personalreminder.service.NotificationService;
import com.example.personalreminder.service.NotificationServiceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReminderJob {
    private final NotificationServiceFactory notificationServiceFactory;

    @Scheduled(cron = "*/15 * * * * *", zone = "Asia/Ho_Chi_Minh")
    public void handle() {
        log.info("======== CRON LOG");
//        NotificationService notificationService = notificationServiceFactory.get("email");
//        notificationService.send("thu.pa@ascendcorp.com", "Hello World!");
        NotificationService notificationService = notificationServiceFactory.get("slack");
        notificationService.send(null, "Message from Reminder Spring APP!");
    }
}
