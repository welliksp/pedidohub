package br.com.wsp.controller.v1;

import br.com.wsp.dto.PedidoRequest;
import br.com.wsp.dto.PedidoResponse;
import br.com.wsp.service.IPedidoService;
import br.com.wsp.service.impl.PedidoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/pedido")
public class PedidoController {

    private final IPedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PedidoRequest request) throws Exception {

        PedidoResponse saved = service.save(request);

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<Page<PedidoResponse>> findAll(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> update(@RequestBody @Valid PedidoRequest request, @PathVariable UUID id) {

        return ResponseEntity.ok(service.update(id, request).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/pagamento")
    public ResponseEntity<?> pay(@PathVariable UUID id) throws Exception {

        Optional<PedidoResponse> pagar = service.pagar(id);

        return ResponseEntity.ok(pagar);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getPedidosDoUsuarioAutenticado(@PathVariable Long id) {

        List<PedidoResponse> pedidoResponses = service.findByUser(id);

        return ResponseEntity.ok(pedidoResponses);
    }

}
