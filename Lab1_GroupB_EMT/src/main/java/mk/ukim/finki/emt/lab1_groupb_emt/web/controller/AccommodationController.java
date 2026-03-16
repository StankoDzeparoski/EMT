package mk.ukim.finki.emt.lab1_groupb_emt.web.controller;

import jakarta.validation.Valid;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.CreateAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.DisplayAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccomodationDto> findById(@PathVariable Long id) {
        return accommodationApplicationService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DisplayAccomodationDto>> findAll() {
        return ResponseEntity.ok(accommodationApplicationService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayAccomodationDto> create(@RequestBody @Valid CreateAccomodationDto createProductDto) {
        return ResponseEntity.ok(accommodationApplicationService.create(createProductDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayAccomodationDto> update(
            @PathVariable Long id,
            @RequestBody CreateAccomodationDto createProductDto
    ) {
        return accommodationApplicationService
                .update(id, createProductDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayAccomodationDto> deleteById(@PathVariable Long id) {
        return accommodationApplicationService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}