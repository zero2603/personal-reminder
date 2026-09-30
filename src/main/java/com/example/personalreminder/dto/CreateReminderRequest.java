package com.example.personalreminder.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Size(max = 10)
    public String repeat;
}
