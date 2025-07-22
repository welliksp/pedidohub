package br.com.wsp.handler;

import br.com.wsp.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public final ResponseEntity<ExceptionResponse> handleEstoqueInsuficienteException(Exception ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @ExceptionHandler(PedidoProcessadoException.class)
    public final ResponseEntity<ExceptionResponse> handlePedidoProcessadoException(Exception ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(UsuarioCadastradoException.class)
    public final ResponseEntity<ExceptionResponse> handleUsuarioCadastradoException(Exception ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);

    }


}
