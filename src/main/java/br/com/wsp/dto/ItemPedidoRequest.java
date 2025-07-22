package br.com.wsp.dto;

import java.util.UUID;

public record ItemPedidoRequest(UUID produtoId, int quantidade) {
}
