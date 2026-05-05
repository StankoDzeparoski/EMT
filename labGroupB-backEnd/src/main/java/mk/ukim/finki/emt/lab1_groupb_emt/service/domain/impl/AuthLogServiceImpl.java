package mk.ukim.finki.emt.lab1_groupb_emt.service.domain.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.lab1_groupb_emt.helpers.JwtHelper;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.AuthLog;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Auth.AuthLogDto;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.AuthLogRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.AuthLogService;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthLogServiceImpl implements AuthLogService {

    private final AuthLogRepository authLogRepository;
    private final JwtHelper jwtHelper;

    @Override
    public void saveLog(String username, String token) {

        AuthLog authLog = AuthLog.builder()
                .username(username)
                .token(token)
                .issuedAt(
                        jwtHelper.extractIssuedAt(token)
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                )
                .expiresAt(
                        jwtHelper.extractExpiration(token)
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                )
                .build();

        authLogRepository.save(authLog);
    }

    @Override
    public List<AuthLogDto> findAll() {
        return authLogRepository.findAll()
                .stream()
                .map(log -> new AuthLogDto(
                        log.getId(),
                        log.getUsername(),
                        log.getToken(),
                        log.getIssuedAt(),
                        log.getExpiresAt()
                ))
                .toList();
    }
}