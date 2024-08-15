package com.client.api.ws.rasmooplus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTypeDTO {

    private Long id;
    @NotBlank(message = "Can't be blank or null")
    @Size(min = 3, message = "Minimum size is 3")
    private String name;
    @NotBlank(message = "Can't be blank or null")
    @Size(min = 10, message = "Minimum size is 10")
    private String description;
}
