package com.devsuperior.bds02.resources;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityResource {

    @Autowired
    private CityService service;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAll() {
        List<CityDTO> result = service.findAll();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CityDTO> insertResource(@RequestBody CityDTO dto) {
        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
