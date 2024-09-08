package com.client.api.ws.rasmooplus.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {

    @Email(message = "invalid")
    private String email;

    @NotBlank(message = "invalid attribute")
    private String password;

    private String recoveryCode;
}
