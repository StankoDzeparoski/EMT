package mk.ukim.finki.emt.lab1_groupb_emt.web.controller;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.User;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Auth.*;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.UserApplicationService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.AuthLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserApplicationService userApplicationService;
    private final AuthLogService authLogService;

    public UserController(UserApplicationService userApplicationService, AuthLogService authLogService) {
        this.userApplicationService = userApplicationService;
        this.authLogService = authLogService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<RegisterUserResponseDto> findByUsername(@PathVariable String username) {
        return userApplicationService
            .findByUsername(username)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    public ResponseEntity<RegisterUserResponseDto> me(@AuthenticationPrincipal User user) {
        return userApplicationService
            .findByUsername(user.getUsername())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
        return userApplicationService
            .register(registerUserRequestDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> login(@RequestBody LoginUserRequestDto loginUserRequestDto) {
        return userApplicationService
            .login(loginUserRequestDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.badRequest().build());
    }

//    @GetMapping("/auth-logs")
//    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
//    public List<AuthLogDto> getAuthLogs() {
//        return authLogService.findAll();
//    }
    @GetMapping("/auth-logs")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<List<AuthLogDto>> getAuthLogs() {
        return ResponseEntity.ok(authLogService.findAll());
    }
}
