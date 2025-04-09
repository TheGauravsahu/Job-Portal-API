package com.jobportal.entity;

import com.jobportal.dto.UserDTO;
import com.jobportal.dto.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    private Boolean isVerified = false;
    private UserRoles role;

    public UserDTO toDTO() {
        return new UserDTO(this.id, this.name, this.email, this.password,this.role);
    }
}
