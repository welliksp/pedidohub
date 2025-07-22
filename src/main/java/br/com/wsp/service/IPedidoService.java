package br.com.wsp.service;

import br.com.wsp.dto.PedidoRequest;
import br.com.wsp.dto.PedidoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoService {

    PedidoResponse save(PedidoRequest produto) throws Exception;

    Page<PedidoResponse> findAll(Pageable pageable);

    Optional<PedidoResponse> update(UUID id, PedidoRequest request);

    Optional<PedidoResponse> findById(UUID id);

    void delete(UUID id) throws Exception;

    Optional<PedidoResponse> pagar(UUID pedidoId);

    List<PedidoResponse> findByUser(Long id);
}
