package com.example.personalreminder.entity;

import com.example.personalreminder.constant.RepeatEnum;
import com.example.personalreminder.converter.RepeatEnumConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reminders")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "remind_date", nullable = false)
    private String remindDate;

    @Column(name = "remind_time", nullable = false)
    private String remindTime;

    @Column(name = "repeat", nullable = false)
    @Convert(converter = RepeatEnumConverter.class)
    private RepeatEnum repeat;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
