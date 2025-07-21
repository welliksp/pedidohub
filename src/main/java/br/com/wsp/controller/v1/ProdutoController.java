package br.com.wsp.controller.v1;

import br.com.wsp.dto.ProdutoRequest;
import br.com.wsp.dto.ProdutoResponse;
import br.com.wsp.dto.ProdutoRequest;
import br.com.wsp.dto.ProdutoResponse;
import br.com.wsp.service.IProdutoService;
import br.com.wsp.service.impl.ProdutoService;
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
@RequestMapping("/v1/produto")
public class ProdutoController {

    private final IProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ProdutoRequest request) throws Exception {

        Optional<ProdutoResponse> saved = service.save(request);

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> findAll(@PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> update(@RequestBody @Valid ProdutoRequest request, @PathVariable UUID id) {

        return ResponseEntity.ok(service.update(id, request).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
