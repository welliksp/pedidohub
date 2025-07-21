package br.com.wsp.service.impl;

import br.com.wsp.dto.CategoriaRequest;
import br.com.wsp.dto.CategoriaResponse;
import br.com.wsp.entity.Categoria;
import br.com.wsp.repository.CategoriaRepository;
import br.com.wsp.service.ICategoriaService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Optional<CategoriaResponse> save(CategoriaRequest request) {
        log.debug("Iniciando salvamento de nova categoria: {}", request);

        Categoria categoria = Categoria.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .build();
        
        
        Categoria saved = repository.save(categoria);
        log.info("Categoria salva com sucesso. ID: {}", saved.getId());

        return Optional.of(new CategoriaResponse(saved.getId(), saved.getNome(), saved.getDescricao()));
    }

    @Override
    @Transactional
    public Page<CategoriaResponse> findAll(Pageable pageable) {
        log.debug("Buscando todas as categorias. Pageable: {}", pageable);

        return repository.findAll(pageable).map(c -> new CategoriaResponse(c.getId(), c.getNome(), c.getDescricao()));
    }

    @Override
    @Transactional
    public Optional<CategoriaResponse> update(UUID id, CategoriaRequest request) {
        log.debug("Iniciando atualização da categoria. ID: {}, Request: {}", id, request);

        Optional<Categoria> categoria = repository.findById(id);

        if (categoria.isEmpty()) {
            return Optional.empty();
        }

        Categoria toUpdate = categoria.get();
        toUpdate.setNome(request.nome());
        toUpdate.setDescricao(request.descricao());

        Categoria updated = repository.save(toUpdate);
        log.info("Categoria atualizada com sucesso. ID: {}", updated.getId());

        return Optional.of(new CategoriaResponse(updated.getId(), updated.getNome(), updated.getDescricao()));
    }

    @Override
    public Optional<CategoriaResponse> findById(UUID id) {
        log.debug("Buscando categoria por ID: {}", id);

        Optional<Categoria> categoria = repository.findById(id);

        return Optional.of(new CategoriaResponse(categoria.get().getId(), categoria.get().getNome(), categoria.get().getDescricao()));
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        log.debug("Iniciando exclusão da categoria. ID: {}", id);

        validaCategoriaExistente(id);

        repository.deleteById(id);
        log.info("Categoria excluída com sucesso. ID: {}", id);
    }

    private void validaCategoriaExistente(UUID id) throws Exception {

        findById(id).orElseThrow(Exception::new);
    }
}
