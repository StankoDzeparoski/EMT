package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Auth;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.User;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Role;

public record RegisterUserResponseDto(
    String username,
    String name,
    String surname,
    String email,
    Role role
) {
    public static RegisterUserResponseDto from(User user) {
        return new RegisterUserResponseDto(
            user.getUsername(),
            user.getName(),
            user.getSurname(),
            user.getEmail(),
            user.getRole()
        );
    }
}


