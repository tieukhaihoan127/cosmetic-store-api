package com.vincent.beauty_shop.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DeleteForeignKeyException extends DataIntegrityViolationException {
    public DeleteForeignKeyException(String message){
        super(message);
    }
}
