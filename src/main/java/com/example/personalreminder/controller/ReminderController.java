package com.example.personalreminder.controller;

import com.example.personalreminder.dto.CreateReminderRequest;
import com.example.personalreminder.dto.UpdateReminderRequest;
import com.example.personalreminder.entity.Reminder;
import com.example.personalreminder.security.UserPrincipal;
import com.example.personalreminder.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {
    private final ReminderService reminderService;

    @GetMapping("")
    public ResponseEntity<List<Reminder>> getAllReminders() {
        return ResponseEntity.ok(reminderService.getAllReminders());
    }

    @PostMapping("")
    public ResponseEntity<Reminder> createReminder(@Valid @RequestBody CreateReminderRequest payload,
                                                   @AuthenticationPrincipal UserPrincipal currentUser) {
        Reminder createdReminder = reminderService.createReminder(currentUser.id(), payload);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdReminder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable Long id,
                                   @RequestBody UpdateReminderRequest payload,
                                   @AuthenticationPrincipal UserPrincipal currentUser) {
        Reminder updatedReminder = reminderService.updateReminder(currentUser.id(), id, payload);
        if (updatedReminder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reminder not found");
        }
        return ResponseEntity.ok(updatedReminder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id,
                                               @AuthenticationPrincipal UserPrincipal currentUser) {
        reminderService.deleteReminder(currentUser.id(), id);
        return ResponseEntity.noContent().build();
    }
}
