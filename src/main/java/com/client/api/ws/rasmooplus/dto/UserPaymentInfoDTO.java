package com.client.api.ws.rasmooplus.dto;

import com.client.api.ws.rasmooplus.model.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPaymentInfoDTO {

    private Long id;

    @Size(min = 16, max = 16, message = "Must have 16 digits")
    private String cardNumber;

    @Min(value = 1)
    @Max(value = 12)
    private Long cardExpirationMonth;
    private Long cardExpirationYear;

    @Size(min = 3, max = 3, message = "Must have 3 digits")
    private String cardSecurityCode;
    private BigDecimal price;
    private Long installments;
    private LocalDate dtPayment = LocalDate.now();

    @NotNull(message = "Must be informed")
    private Long userId;
}
