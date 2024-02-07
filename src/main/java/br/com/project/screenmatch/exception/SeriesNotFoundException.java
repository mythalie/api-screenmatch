package br.com.project.screenmatch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SeriesNotFoundException extends RuntimeException {
    public SeriesNotFoundException() {
        super("Série não encontrada!");
    }
}
