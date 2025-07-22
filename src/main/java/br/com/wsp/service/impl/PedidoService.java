package br.com.wsp.service.impl;

import br.com.wsp.dto.ItemPedidoResponse;
import br.com.wsp.dto.PedidoRequest;
import br.com.wsp.dto.PedidoResponse;
import br.com.wsp.dto.ProdutoResponse;
import br.com.wsp.entity.*;
import br.com.wsp.enums.StatusPedido;
import br.com.wsp.repository.ItemPedidoRepository;
import br.com.wsp.repository.PedidoRepository;
import br.com.wsp.repository.ProdutoRepository;
import br.com.wsp.repository.UserRepository;
import br.com.wsp.service.IPedidoService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PedidoService implements IPedidoService {

    private final PedidoRepository repository;
    private final ProdutoRepository produtoRepository;
    private final UserRepository userRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository repository, ProdutoRepository produtoRepository, UserRepository userRepository, ItemPedidoRepository itemPedidoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
        this.userRepository = userRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }


    @Transactional
    @Override
    public PedidoResponse save(PedidoRequest request) {
        Optional<User> user = userRepository.findById(request.userId());

        Pedido pedido = Pedido.builder()
                .status(StatusPedido.PENDENTE)
                .user(user.get())
                .build();

        List<ItemPedido> itemPedidoList = request.itens().stream().map(item -> {
            Produto produto = produtoRepository.findById(item.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            return ItemPedido.builder()
                    .produto(produto)
                    .quantidade(item.quantidade())
                    .pedido(pedido)
                    .build();
        }).toList();

        pedido.setItens(itemPedidoList);

        Pedido saved = repository.save(pedido);

        List<ItemPedidoResponse> itemPedidoResponseList = saved.getItens().stream().map(item -> {
            Produto p = item.getProduto();
            ProdutoResponse response = new ProdutoResponse(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getCategoria().getNome(), p.getQuantidadeEstoque());
            return new ItemPedidoResponse(item.getId(), response, item.getQuantidade());
        }).toList();

        return new PedidoResponse(saved.getId(), saved.getStatus(), itemPedidoResponseList);
    }

    @Override
    @Transactional
    public Page<PedidoResponse> findAll(Pageable pageable) {
        log.debug("Buscando todas as categorias. Pageable: {}", pageable);

        return repository.findAll(pageable).map(pedido -> {
            List<ItemPedidoResponse> itemPedidoResponseList = new ArrayList<>();
            pedido.getItens().forEach(item -> {
                ProdutoResponse response = new ProdutoResponse(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getProduto().getDescricao(),
                        item.getProduto().getPreco(),
                        item.getProduto().getCategoria().getNome(),
                        item.getProduto().getQuantidadeEstoque()
                );
                ItemPedidoResponse itemPedidoResponse = new ItemPedidoResponse(
                        item.getId(),
                        response,
                        item.getQuantidade()
                );
                itemPedidoResponseList.add(itemPedidoResponse);
            });
            return new PedidoResponse(
                    pedido.getId(),
                    pedido.getStatus(),
                    itemPedidoResponseList
            );
        });
    }

    @Override
    @Transactional
    public Optional<PedidoResponse> update(UUID id, PedidoRequest request) {
        log.debug("Iniciando atualização do pedido. ID: {}, Request: {}", id, request);

        Optional<Pedido> optionalPedido = repository.findById(id);
        if (optionalPedido.isEmpty()) {
            return Optional.empty();
        }

        Pedido pedido = optionalPedido.get();

        Optional<User> user = userRepository.findById(request.userId());
        if (user.isEmpty()) {
            return Optional.empty();
        }
        pedido.setUser(user.get());

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        request.itens().forEach(item -> {
            Optional<Produto> produtoOpt = produtoRepository.findById(item.produtoId());
            if (produtoOpt.isEmpty()) {
                return;
            }

            ItemPedido itemPedido = ItemPedido.builder()
                    .produto(produtoOpt.get())
                    .quantidade(item.quantidade())
                    .pedido(pedido)
                    .build();

            itemPedidoList.add(itemPedido);
        });


        pedido.getItens().clear();
        pedido.getItens().addAll(itemPedidoList);

        Pedido updated = repository.save(pedido);

        List<ItemPedidoResponse> itemPedidoResponseList = updated.getItens().stream().map(item -> {
            Produto produto = item.getProduto();
            ProdutoResponse response = new ProdutoResponse(
                    produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getPreco(),
                    produto.getCategoria().getNome(),
                    produto.getQuantidadeEstoque()
            );
            return new ItemPedidoResponse(item.getId(), response, item.getQuantidade());
        }).toList();

        PedidoResponse pedidoResponse = new PedidoResponse(
                updated.getId(),
                updated.getStatus(),
                itemPedidoResponseList
        );

        return Optional.of(pedidoResponse);
    }


    @Override
    public Optional<PedidoResponse> findById(UUID id) {
        log.debug("Buscando pedido por ID: {}", id);

        Optional<Pedido> optionalPedido = repository.findById(id);
        if (optionalPedido.isEmpty()) {
            return Optional.empty();
        }

        Pedido pedido = optionalPedido.get();
        List<ItemPedidoResponse> itemPedidoResponseList = new ArrayList<>();

        extracted(pedido, itemPedidoResponseList);

        log.info("Pedido encontrado com sucesso. ID: {}", id);
        return Optional.of(new PedidoResponse(
                pedido.getId(),
                pedido.getStatus(),
                itemPedidoResponseList
        ));
    }

    private static void extracted(Pedido pedido, List<ItemPedidoResponse> itemPedidoResponseList) {
        pedido.getItens().stream().forEach(item -> {
            ProdutoResponse response = new ProdutoResponse(
                    item.getProduto().getId(),
                    item.getProduto().getNome(),
                    item.getProduto().getDescricao(),
                    item.getProduto().getPreco(),
                    item.getProduto().getCategoria().getNome(),
                    item.getProduto().getQuantidadeEstoque()
            );
            ItemPedidoResponse itemPedidoResponse = new ItemPedidoResponse(
                    item.getId(),
                    response,
                    item.getQuantidade()
            );
            itemPedidoResponseList.add(itemPedidoResponse);
        });
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        log.debug("Iniciando exclusão do pedido. ID: {}", id);

        validaPedidoExistente(id);

        repository.deleteById(id);
        log.info("Pedido excluído com sucesso. ID: {}", id);
    }

    @Override
    @Transactional
    public Optional<PedidoResponse> pagar(UUID pedidoId) {
        Optional<Pedido> optionalPedido = repository.findById(pedidoId);
        if (optionalPedido.isEmpty()) {
            return Optional.empty();
        }

        Pedido pedido = optionalPedido.get();
        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            throw new IllegalStateException("Pedido já processado.");
        }

        BigDecimal totalCalculado = BigDecimal.ZERO;

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produto.getQuantidadeEstoque() < item.getQuantidade()) {
                pedido.setStatus(StatusPedido.CANCELADO);
                repository.save(pedido);
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            totalCalculado = totalCalculado.add(subtotal);
        }

        pedido.setTotal(totalCalculado);

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId()).get();
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
            produtoRepository.save(produto);
        }

        pedido.setStatus(StatusPedido.PAGO);
        Pedido pago = repository.save(pedido);

        List<ItemPedidoResponse> itens = pago.getItens().stream().map(item -> {
            Produto p = item.getProduto();
            ProdutoResponse produtoResponse = new ProdutoResponse(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getCategoria().getNome(), p.getQuantidadeEstoque());
            return new ItemPedidoResponse(item.getId(), produtoResponse, item.getQuantidade());
        }).toList();

        return Optional.of(new PedidoResponse(pago.getId(), pago.getStatus(), itens));
    }

    private void validaPedidoExistente(UUID id) throws Exception {
        findById(id).orElseThrow(() ->
                new Exception("Pedido não encontrado com ID: " + id));
    }
}
