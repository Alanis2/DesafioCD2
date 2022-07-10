package br.com.cd2.sigabem.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {
    private int status;
    private String title;
    private LocalDateTime timeStamp;
    private String details;
}