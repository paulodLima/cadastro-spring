package com.person.api.exception;

public enum MessageErrorImpl implements MessageError{

    MALFORMED_REQUEST_JSON(1),
    JSON_TO_OBJECT_FAIL(2),
    OBJECT_TO_JSON_FAIL(3),
    SERVICE_UNAVAILABLE(4),
    PERSON(5),
    OPERATOR(7),
    RESOURCE_NOT_FOUND(6),
    ADDRESS(7);

    private Integer code;

    MessageErrorImpl(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
