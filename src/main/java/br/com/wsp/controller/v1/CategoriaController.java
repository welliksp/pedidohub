package br.com.wsp.controller.v1;

import br.com.wsp.dto.CategoriaRequest;
import br.com.wsp.dto.CategoriaResponse;
import br.com.wsp.service.ICategoriaService;
import br.com.wsp.service.impl.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categoria")
public class CategoriaController {

    private final ICategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> save(@RequestBody @Valid CategoriaRequest request) {

        Optional<CategoriaResponse> saved = service.save(request);

        return ResponseEntity.ok(saved.get());
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaResponse>> findAll(@PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> update(@RequestBody @Valid CategoriaRequest request, @PathVariable UUID id) {

        return ResponseEntity.ok(service.update(id, request).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
