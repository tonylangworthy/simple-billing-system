package com.langworthytech.simplebillingsystem.security;

import java.util.ArrayList;
import java.util.List;

public class RegistrationValidationError {

    private List<String> errors = new ArrayList<>();

    private String fieldName;

    private boolean hasErrors = false;

    private String errorMessage;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
