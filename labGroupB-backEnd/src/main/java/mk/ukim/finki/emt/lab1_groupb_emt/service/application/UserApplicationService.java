package mk.ukim.finki.emt.lab1_groupb_emt.service.application;

import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Auth.LoginUserRequestDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Auth.LoginUserResponseDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Auth.RegisterUserRequestDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Auth.RegisterUserResponseDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto);

    Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto);

    Optional<RegisterUserResponseDto> findByUsername(String username);
}
