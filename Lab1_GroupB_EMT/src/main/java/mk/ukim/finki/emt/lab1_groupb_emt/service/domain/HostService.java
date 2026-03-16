package mk.ukim.finki.emt.lab1_groupb_emt.service.domain;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    Optional<Host> findById(Long id);

    List<Host> findAll();

    Host create(Host product);

    Optional<Host> update(Long id, Host product);

    Optional<Host> deleteById(Long id);
}
