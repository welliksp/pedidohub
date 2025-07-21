package br.com.wsp.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponse(UUID id, String nome, String descricao, BigDecimal preco, String categoria, int quantidadeEstoque) {
}
