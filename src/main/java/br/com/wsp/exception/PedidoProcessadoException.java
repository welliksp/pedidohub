package br.com.wsp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PedidoProcessadoException extends RuntimeException {
    public PedidoProcessadoException(String message) {
        super(message);
    }
}
