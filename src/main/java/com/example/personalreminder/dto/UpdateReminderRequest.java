package com.example.personalreminder.dto;

import com.example.personalreminder.constant.RepeatEnum;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class UpdateReminderRequest {
    public String name;

    @Size(min = 2, max = 2)
    public String remindDate;

    @Size(min = 5, max = 5)
    public String remindTime;

    public RepeatEnum repeat;
}
