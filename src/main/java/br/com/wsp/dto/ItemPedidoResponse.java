package br.com.wsp.dto;

import java.util.UUID;

public record ItemPedidoResponse(UUID id, ProdutoResponse produto, int quantidade) {
}
