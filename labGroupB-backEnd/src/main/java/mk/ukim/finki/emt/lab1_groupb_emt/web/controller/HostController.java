package mk.ukim.finki.emt.lab1_groupb_emt.web.controller;

import jakarta.validation.Valid;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.CreateHostDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.DisplayHostDto;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.HostApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayHostDto>> findAll() {
        return ResponseEntity.ok(hostApplicationService.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<DisplayHostDto>> findAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ) {
        return ResponseEntity.ok(hostApplicationService.findAll(page, size, sortBy));
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> create(@RequestBody @Valid CreateHostDto createProductDto) {
        return ResponseEntity.ok(hostApplicationService.create(createProductDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayHostDto> update(
            @PathVariable Long id,
            @RequestBody CreateHostDto createProductDto
    ) {
        return hostApplicationService
                .update(id, createProductDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayHostDto> deleteById(@PathVariable Long id) {
        return hostApplicationService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostApplicationService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}



