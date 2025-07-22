package br.com.wsp.dto;

import java.util.List;

public record PedidoRequest(List<ItemPedidoRequest> itens, Long userId) {
}
