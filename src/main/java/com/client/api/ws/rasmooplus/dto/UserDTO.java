package com.client.api.ws.rasmooplus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    @NotBlank(message = "Can't be null or blank")
    @Size(min= 6, message = "Minimum value is 6")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @Size(min= 11, message = "Minimum valuesis 11 digits")
    private String phone;

    @CPF(message = "Invalid CPF")
    private String cpf;


    private LocalDate dtSubscription = LocalDate.now();
    private LocalDate dtExpiration = LocalDate.now();

    @NotNull(message = "Can't be null")
    private Long userTypeId;
    private Long subscriptionTypeId;
}
