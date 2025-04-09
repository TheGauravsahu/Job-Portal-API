package com.jobportal.dto;
import com.jobportal.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {


    private String id;

    @NotBlank(message = "Name can't be blank.")
    private String name;

    @NotBlank(message = "Email can't be blank.")
    @Email(message = "Invalid email format.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Password can't be blank.")
    @Size(min = 6, message = "Password must be at least 6 characters long.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit. Special characters are optional but allowed."
    )
    private String password;

    private UserRoles role;

    public User toEntity() {
        return new User(this.id, this.name, this.email, this.password, false, this.role);
    }
}
