package br.com.wsp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UserRequest(@NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName,
                          @NotNull @NotEmpty @Email String email, @NotNull Date birthdate,
                          @NotNull @NotEmpty String password) {
}
