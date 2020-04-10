package io.github.makbn.api;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Builder
@Data
public class Response<T> {
    private boolean error;
    private String message;
    private T result;
    private Date time;
}
