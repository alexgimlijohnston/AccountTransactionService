package com.service.common.exceptions;

import javax.ws.rs.BadRequestException;

public class AccountDoesNotExistException extends BadRequestException {

    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
