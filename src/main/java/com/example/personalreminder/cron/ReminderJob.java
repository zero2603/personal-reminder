package com.example.personalreminder.cron;

import com.example.personalreminder.entity.Reminder;
import com.example.personalreminder.entity.User;
import com.example.personalreminder.service.NotificationService;
import com.example.personalreminder.service.NotificationServiceFactory;
import com.example.personalreminder.service.ReminderService;
import com.example.personalreminder.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReminderJob {
    private final NotificationServiceFactory notificationServiceFactory;
    private final UserService userService;
    private final ReminderService reminderService;

    @Scheduled(cron = "*/15 * * * * *", zone = "Asia/Ho_Chi_Minh")
    public void handle() {
        log.info("======== CRON LOG");
        List<User> users = userService.getAll();

        for (User user : users) {
            List<Reminder> records = reminderService.getPaymentsDueToday(user.getId());

            if (!records.isEmpty()) {
                String message = "There are " + records.stream().count() + " service(s) need to be renewed!";
                NotificationService notificationService = notificationServiceFactory.get("email");
                notificationService.send(user.getEmail(), message);
//                NotificationService notificationService = notificationServiceFactory.get("slack");
//                notificationService.send(null, message);
            }
        }
    }
}
