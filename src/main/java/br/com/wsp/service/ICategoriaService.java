package br.com.wsp.service;

import br.com.wsp.dto.CategoriaRequest;
import br.com.wsp.dto.CategoriaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ICategoriaService {

    Optional<CategoriaResponse> save(CategoriaRequest categoria);

    Page<CategoriaResponse> findAll(Pageable pageable);

    Optional<CategoriaResponse> update(UUID id, CategoriaRequest request);

    Optional<CategoriaResponse> findById(UUID id);

    void delete(UUID id) throws Exception;

}
