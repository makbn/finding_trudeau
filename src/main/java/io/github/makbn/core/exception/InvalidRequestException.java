package io.github.makbn.core.exception;

import lombok.Builder;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
    }

    @Builder
    public InvalidRequestException(String message) {
        super(message);
    }
}
