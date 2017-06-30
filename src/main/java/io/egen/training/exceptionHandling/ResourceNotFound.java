package io.egen.training.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/*
* ResourceNotFound is a child class of RunTimeException
* Used for throwing runtime exceptions with message and error code corresponding to NOT FOUND
* */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
}
