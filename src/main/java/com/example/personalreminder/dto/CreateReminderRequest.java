package com.example.personalreminder.dto;

import com.example.personalreminder.constant.RepeatEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class CreateReminderRequest {
    @NotBlank
    public String name;

    @NotBlank
    @Size(min = 2, max = 2)
    public String remindDate;

    @NotBlank
    @Size(min = 5, max = 5)
    public String remindTime;

    @NotNull
    public RepeatEnum repeat;
}
