package com.client.api.ws.rasmooplus.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionTypeDTO {

    private Long id;
    @NotBlank(message = "Name can't be null or blank")
    @Size(min=5, max=30)
    private String name;
    @Max(value=12, message = "Access Month can't be greater then 12")
    private Long accessMonth;
    @NotNull(message = "Price can't be null")
    private BigDecimal price;
    @NotBlank(message = "Product Key can't be null or blank")
    @Size(min=5, max=15, message = "Product key must have between 5 and 15 chars")
    private String productKey;
}
