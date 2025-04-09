package com.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {
    @NotBlank(message = "Email can't be blank.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Old Password can't be blank.")
    @Size(min = 6, message = "Old Password must be at least 6 characters long.")
    private String oldPassword;

    @NotBlank(message = "Password can't be blank.")
    @Size(min = 6, message = "Password must be at least 6 characters long.")
    private String password;
}
