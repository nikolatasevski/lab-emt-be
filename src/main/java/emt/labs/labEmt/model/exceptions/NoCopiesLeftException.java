package emt.labs.labEmt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoCopiesLeftException extends RuntimeException {
    public NoCopiesLeftException(Long id) {
        super(String.format("Book with id %d does not have an available copy!", id));
    }
}
