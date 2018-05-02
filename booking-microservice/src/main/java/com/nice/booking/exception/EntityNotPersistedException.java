package com.nice.booking.exception;

public class EntityNotPersistedException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    public EntityNotPersistedException(String message)
    {
        super(message);
    }
}
