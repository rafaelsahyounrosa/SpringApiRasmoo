package com.client.api.ws.rasmooplus.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDTO {
    private String message;
    private HttpStatus status;
    private Integer statusCode;
}
