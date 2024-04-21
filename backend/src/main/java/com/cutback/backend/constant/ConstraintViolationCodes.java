package com.cutback.backend.constant;

public interface ConstraintViolationCodes {

    String REQUIRED = "required";
    String LENGTH = "length(min: {min}, max: {max})";
    String UNIQUE = "unique";
}
