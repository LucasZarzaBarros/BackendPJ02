package com.autoCenterSilva.demo.dto.response;

import java.time.LocalDateTime;

public record ErroValidacoesResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message)
{
    public ErroValidacoesResponse(int status, String error, String message) {
        this(LocalDateTime.now(), status, error, message);
    }
}
