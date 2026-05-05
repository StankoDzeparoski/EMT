package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Auth;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.User;

public record RegisterUserRequestDto(
    String name,
    String surname,
    String email,
    String username,
    String password
) {
    public User toUser() {
        return new User(name, surname, email, username, password);
    }
}
