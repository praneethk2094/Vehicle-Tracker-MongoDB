package io.egen.training.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
* BadRequest is a child class of RunTimeException
* Used for throwing runtime exceptions with message and error code corresponding to BAD REQUEST
* */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}
