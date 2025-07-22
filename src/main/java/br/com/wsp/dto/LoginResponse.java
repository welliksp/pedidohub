package br.com.wsp.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
