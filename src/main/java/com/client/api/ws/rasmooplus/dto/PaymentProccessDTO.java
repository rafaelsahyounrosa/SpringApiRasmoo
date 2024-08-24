package com.client.api.ws.rasmooplus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProccessDTO {

    @NotBlank(message = "Must be informed")
    private String productKey;
    private BigDecimal discount;

    @NotNull(message = "Must be informed")
    @JsonProperty("userPaymentInfo")
    private UserPaymentInfoDTO userPaymentInfoDto;
}
