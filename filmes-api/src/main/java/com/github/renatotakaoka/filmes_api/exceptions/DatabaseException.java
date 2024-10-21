package com.github.renatotakaoka.filmes_api.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String msg) {
        super(msg);
    }

}