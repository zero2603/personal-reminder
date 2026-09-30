package com.example.personalreminder.service;

import com.example.personalreminder.dto.CreateReminderRequest;
import com.example.personalreminder.dto.UpdateReminderRequest;
import com.example.personalreminder.entity.Reminder;
import com.example.personalreminder.repository.ReminderRepository;
import com.example.personalreminder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final UserRepository userRepository;

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public Reminder createReminder(Long userId, CreateReminderRequest payload) {
        Reminder record = new Reminder();
        record.setUser(userRepository.getReferenceById(userId)); // getReferenceById doesn't touch DB
        record.setName(payload.getName());
        record.setRemindDate(payload.getRemindDate());
        record.setRemindTime(payload.getRemindTime());
        record.setRepeat(payload.getRepeat());

        return reminderRepository.save(record);
    }

    public Reminder updateReminder(Long userId, Long id, UpdateReminderRequest payload) {
        Optional<Reminder> result = reminderRepository.findByIdAndUserId(id, userId);

        if (result.isEmpty()) return null;

        Reminder record = result.get();

        if (payload.getName() != null && !payload.getName().isEmpty()) {
            record.setName(payload.getName());
        }
        if (payload.getRemindDate() != null && !payload.getRemindDate().isEmpty()) {
            record.setRemindDate(payload.getRemindDate());
        }
        if (payload.getRemindTime() != null && !payload.getRemindTime().isEmpty()) {
            record.setRemindTime(payload.getRemindTime());
        }
        if (payload.getRepeat() != null && !payload.getRepeat().isEmpty()) {
            record.setRepeat(payload.getRepeat());
        }

        return reminderRepository.save(record);
    }

    public void deleteReminder(Long userId, Long id) {
        Reminder record = reminderRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reminder not found"));
        reminderRepository.delete(record);
    }
}
