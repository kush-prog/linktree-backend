package com.linktr.backend.linktr_backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timeStamp;
}
