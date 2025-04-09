package com.jobportal.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
    private Integer errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;
}
