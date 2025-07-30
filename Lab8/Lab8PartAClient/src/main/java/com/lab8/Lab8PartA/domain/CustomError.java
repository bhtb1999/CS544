package com.lab8.Lab8PartA.domain;

public class CustomError {
    private String errorMessage;

    public CustomError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "CustomError{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
