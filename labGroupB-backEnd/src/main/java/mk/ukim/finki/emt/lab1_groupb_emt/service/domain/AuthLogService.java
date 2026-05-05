package mk.ukim.finki.emt.lab1_groupb_emt.service.domain;

import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Auth.AuthLogDto;

import java.util.List;

public interface AuthLogService {

    void saveLog(String username, String token);

    List<AuthLogDto> findAll();
}