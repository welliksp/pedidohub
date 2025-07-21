package br.com.wsp.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoRequest(String nome, String descricao, BigDecimal preco, UUID categoria, int quantidadeEstoque) {
}
