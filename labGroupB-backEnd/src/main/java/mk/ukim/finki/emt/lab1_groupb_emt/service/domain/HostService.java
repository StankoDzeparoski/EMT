package mk.ukim.finki.emt.lab1_groupb_emt.service.domain;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface HostService {
    Optional<Host> findById(Long id);

    List<Host> findAll();

    Host create(Host product);

    Optional<Host> update(Long id, Host product);

    Optional<Host> deleteById(Long id);

    Page<Host> findAll(int page, int size, String sortBy);
}
