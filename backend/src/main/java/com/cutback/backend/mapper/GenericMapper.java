package com.cutback.backend.mapper;

import com.cutback.backend.dto.request.RegisterRequest;
import com.cutback.backend.model.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenericMapper {

    private final ModelMapper mapper;

    public Account toResource(RegisterRequest registerRequest) {
        return mapper.map(registerRequest, Account.class);
    }
}
