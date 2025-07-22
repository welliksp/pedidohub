package br.com.wsp.service.impl;

import br.com.wsp.dto.ProdutoRequest;
import br.com.wsp.dto.ProdutoResponse;
import br.com.wsp.entity.Categoria;
import br.com.wsp.entity.Produto;
import br.com.wsp.exception.NotFoundException;
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

        Categoria categoria = categoriaRepository.findById(request.categoria()).orElseThrow(() -> new NotFoundException("Categoria não encontrada com ID: " + request.categoria() ));;

        Produto produto = Produto.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .preco(request.preco())
                .quantidadeEstoque(request.quantidadeEstoque())
                .categoria(categoria)
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
        Produto produto = repository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrada com ID: " + request.categoria() ));;;

        Categoria categoria = categoriaRepository.findById(request.categoria()).orElseThrow(() -> new NotFoundException("Categoria não encontrada com ID: " + request.categoria() ));;;

        Produto toUpdate = produto;
        toUpdate.setNome(request.nome());
        toUpdate.setDescricao(request.descricao());
        toUpdate.setPreco(request.preco());
        toUpdate.setQuantidadeEstoque(request.quantidadeEstoque());
        toUpdate.setCategoria(categoria);
        toUpdate.setDataAtualizacao(LocalDateTime.now());

        Produto updated = repository.save(toUpdate);
        log.info("Produto atualizado com sucesso. ID: {}", updated.getId());

        return Optional.of(new ProdutoResponse(updated.getId(), updated.getNome(), updated.getDescricao(), updated.getPreco(), updated.getCategoria().getNome(), updated.getQuantidadeEstoque()));
    }

    @Override
    public Optional<ProdutoResponse> findById(UUID id) {
        log.debug("Buscando produtoId por ID: {}", id);
        Produto produto = repository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrada com ID: " + id ));;;


        return Optional.of(new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria().getNome(),
                produto.getQuantidadeEstoque()
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

        findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrada com ID: " + id ));;;
    }
}
