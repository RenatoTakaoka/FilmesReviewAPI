package com.github.renatotakaoka.filmes_api.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String message) {
        super(message);
    }

}