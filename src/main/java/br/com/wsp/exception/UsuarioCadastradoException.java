package br.com.wsp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsuarioCadastradoException extends RuntimeException {
    public UsuarioCadastradoException(String message) {
        super(message);
    }
}
