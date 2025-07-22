package br.com.wsp.dto;

import java.util.Date;

public record UserResponse(Long id, String firstName, String lastName, String username,
                           String email, Date birthdate) {
}
