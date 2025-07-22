package br.com.wsp.service.impl;

import br.com.wsp.dto.ProdutoRequest;
import br.com.wsp.dto.ProdutoResponse;
import br.com.wsp.entity.Categoria;
import br.com.wsp.entity.Produto;
import br.com.wsp.repository.CategoriaRepository;
import br.com.wsp.repository.ProdutoRepository;
import br.com.wsp.service.IProdutoService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository repository, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    @Transactional
    public Optional<ProdutoResponse> save(ProdutoRequest request) throws Exception {
        log.debug("Iniciando salvamento de novo produtoId: {}", request);

        Optional<Categoria> categoria = categoriaRepository.findById(request.categoria());

        if (categoria.isEmpty()) {
            throw new Exception("Categoria não encontrada");
        }

        Produto produto = Produto.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .preco(request.preco())
                .quantidadeEstoque(request.quantidadeEstoque())
                .categoria(categoria.get())
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(null)
                .build();

        Produto saved = repository.save(produto);
        log.info("Produto salvo com sucesso. ID: {}", saved.getId());

        return Optional.of(new ProdutoResponse(saved.getId(), saved.getNome(), saved.getDescricao(), saved.getPreco(), saved.getCategoria().getNome(), saved.getQuantidadeEstoque()));
    }

    @Override
    @Transactional
    public Page<ProdutoResponse> findAll(Pageable pageable) {
        log.debug("Buscando todos os produtos. Pageable: {}", pageable);
        return repository.findAll(pageable).map(p -> new ProdutoResponse(
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getPreco(),
                p.getCategoria().getNome(),
                p.getQuantidadeEstoque()
        ));
    }

    @Override
    @Transactional
    public Optional<ProdutoResponse> update(UUID id, ProdutoRequest request) {
        log.debug("Iniciando atualização do produtoId. ID: {}, Request: {}", id, request);
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            return Optional.empty();
        }

        Optional<Categoria> categoria = categoriaRepository.findById(request.categoria());

        if (categoria.isEmpty()) {
            return Optional.empty();
        }

        Produto toUpdate = produto.get();
        toUpdate.setNome(request.nome());
        toUpdate.setDescricao(request.descricao());
        toUpdate.setPreco(request.preco());
        toUpdate.setQuantidadeEstoque(request.quantidadeEstoque());
        toUpdate.setCategoria(categoria.get());
        toUpdate.setDataAtualizacao(LocalDateTime.now());

        Produto updated = repository.save(toUpdate);
        log.info("Produto atualizado com sucesso. ID: {}", updated.getId());

        return Optional.of(new ProdutoResponse(updated.getId(), updated.getNome(), updated.getDescricao(), updated.getPreco(), updated.getCategoria().getNome(), updated.getQuantidadeEstoque()));
    }

    @Override
    public Optional<ProdutoResponse> findById(UUID id) {
        log.debug("Buscando produtoId por ID: {}", id);
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new ProdutoResponse(
                produto.get().getId(),
                produto.get().getNome(),
                produto.get().getDescricao(),
                produto.get().getPreco(),
                produto.get().getCategoria().getNome(),
                produto.get().getQuantidadeEstoque()
        ));
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        log.debug("Iniciando exclusão do produtoId. ID: {}", id);

        validaProdutoExistente(id);

        repository.deleteById(id);
        log.info("Produto excluído com sucesso. ID: {}", id);
    }

    private void validaProdutoExistente(UUID id) throws Exception {

        findById(id).orElseThrow(Exception::new);
    }
}
