package br.com.wsp.dto;

import br.com.wsp.enums.StatusPedido;

import java.util.List;
import java.util.UUID;

public record PedidoResponse(UUID id, StatusPedido status, List<ItemPedidoResponse> itens) {
}
