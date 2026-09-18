package com.example.personalreminder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    @Size(max = 50)
    public String username;
    @NotBlank @Size(min = 8, max = 255)
    public String password;
}
