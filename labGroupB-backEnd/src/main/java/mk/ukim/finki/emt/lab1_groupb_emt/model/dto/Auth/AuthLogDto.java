package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Auth;

import java.time.LocalDateTime;

public record AuthLogDto (
        Long id,
     String username,
     String token,
     LocalDateTime issuedAt,
     LocalDateTime expiresAt
) {

}