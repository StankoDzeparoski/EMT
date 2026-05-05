package mk.ukim.finki.emt.lab1_groupb_emt.service.domain.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.HostRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.HostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService
{

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Host create(Host host) {
        return hostRepository.save(host);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id).map((existingHost) -> {
            existingHost.setName(host.getName());
            existingHost.setCountry(host.getCountry());
            existingHost.setSurname(host.getSurname());
            return hostRepository.save(existingHost);
        });
    }

    @Override
    public Optional<Host> deleteById(Long id) {
        Optional<Host> host = hostRepository.findById(id);
        host.ifPresent(hostRepository::delete);
        return host;
    }

    @Override
    public Page<Host> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return hostRepository.findAll(pageable);
    }

}


