package br.com.wsp.service;

import br.com.wsp.dto.ProdutoRequest;
import br.com.wsp.dto.ProdutoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IProdutoService {

    Optional<ProdutoResponse> save(ProdutoRequest produto) throws Exception;

    Page<ProdutoResponse> findAll(Pageable pageable);

    Optional<ProdutoResponse> update(UUID id, ProdutoRequest request);

    Optional<ProdutoResponse> findById(UUID id);

    void delete(UUID id) throws Exception;
}
