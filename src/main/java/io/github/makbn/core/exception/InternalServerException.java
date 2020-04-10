package io.github.makbn.core.exception;

import lombok.Builder;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

public class InternalServerException extends Exception {

    public InternalServerException(String message) {
        super(message);
    }

    @Builder
    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
