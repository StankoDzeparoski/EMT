package mk.ukim.finki.emt.lab1_groupb_emt.service.domain;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    User register(User user);

    User login(String username, String password);
}
