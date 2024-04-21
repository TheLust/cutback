package com.cutback.backend.model;

import com.cutback.backend.constant.ConstraintViolationCodes;
import com.cutback.backend.constant.Constraints;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = ConstraintViolationCodes.REQUIRED)
    @Size(
            min = Constraints.USERNAME_MIN,
            max = Constraints.USERNAME_MAX,
            message = ConstraintViolationCodes.LENGTH
    )
    private String username;

    @Column(nullable = false)
    @NotNull(message = ConstraintViolationCodes.REQUIRED)
    @Size(
            min = Constraints.PASSWORD_MIN,
            max = Constraints.PASSWORD_MAX,
            message = ConstraintViolationCodes.LENGTH
    )
    private String password;
    private Role role;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private boolean enabled;
}
