package mk.ukim.finki.emt.lab1_groupb_emt.service.application;

import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.CreateHostDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.DisplayHostDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    Optional<DisplayHostDto> findById(Long id);

    List<DisplayHostDto> findAll();

    DisplayHostDto create(CreateHostDto createHostDto);

    Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto);

    Optional<DisplayHostDto> deleteById(Long id);

    Page<DisplayHostDto> findAll(int page, int size, String sortBy);


}
