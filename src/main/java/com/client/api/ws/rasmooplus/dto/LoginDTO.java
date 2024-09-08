package com.client.api.ws.rasmooplus.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "atributo obrigatório")
    private String username;

    @NotBlank(message = "atributo obrigatório")
    private String password;
}
