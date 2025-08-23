package com.example.HealthCare.DTO;

import com.example.HealthCare.Model.Users;
import lombok.Data;

@Data

public class RegisterRequest {
    private String username;
    private String password;
    private Users.Role role;

}
