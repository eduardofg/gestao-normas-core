package org.tcc.gestao.normas.core.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler({ IntegrityException.class })
    public ResponseEntity<ErrorMessage> handler(final IntegrityException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.from(ex));
    }
    
    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity<ErrorMessage> handler(final BusinessException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.from(ex));
    }
    
    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<ErrorMessage> handler(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.from(ex));
    }
    
    @ExceptionHandler({ InternalServerErrorException.class })
    public ResponseEntity<ErrorMessage> handler(final InternalServerErrorException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessage.from(ex));
    }
    
}