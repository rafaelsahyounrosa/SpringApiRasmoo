package com.client.api.ws.rasmooplus.exceptions.handler;


import com.client.api.ws.rasmooplus.dto.error.ErrorResponseDTO;
import com.client.api.ws.rasmooplus.exceptions.BadRequestException;
import com.client.api.ws.rasmooplus.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Resourcehandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDTO.builder()
                                                            .message(e.getMessage())
                                                            .status(HttpStatus.NOT_FOUND)
                                                            .statusCode(HttpStatus.NOT_FOUND.value())
                                                            .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDTO.builder()
                                                            .message(e.getMessage())
                                                            .status(HttpStatus.BAD_REQUEST)
                                                            .statusCode(HttpStatus.BAD_REQUEST.value())
                                                            .build());
    }
}
