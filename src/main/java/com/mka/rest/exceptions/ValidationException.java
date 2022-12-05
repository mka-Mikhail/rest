package com.mka.rest.exceptions;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ValidationException extends RuntimeException {
    private List<String> errorFieldsMessage;
    public ValidationException(List<String> errorFieldsMessage) {
        super(errorFieldsMessage.stream().collect(Collectors.joining(", ")));
        this.errorFieldsMessage = errorFieldsMessage;
    }
}
