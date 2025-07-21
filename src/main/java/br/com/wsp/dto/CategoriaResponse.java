package br.com.wsp.dto;

import java.util.UUID;

public record CategoriaResponse(UUID id, String nome, String descricao) {
}
