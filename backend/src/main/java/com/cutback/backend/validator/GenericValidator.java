package com.cutback.backend.validator;

import com.cutback.backend.constant.ConstraintViolationCodes;
import com.cutback.backend.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.validation.Errors;

import java.util.Optional;

public class GenericValidator {

    public static <T> void validate(T object, Errors errors) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        for (ConstraintViolation<T> violation : validator.validate(object)) {
            errors.rejectValue(violation.getPropertyPath().toString(), "", violation.getMessage());
        }
    }

    public static <T> void unique(Errors errors, String field, Optional<T> found) {
        if (found.isPresent()) {
            errors.rejectValue(field, "", ConstraintViolationCodes.UNIQUE);
        }
    }

    public static void throwValidationException(Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
    }
}
