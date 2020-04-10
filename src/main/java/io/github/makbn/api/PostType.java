package io.github.makbn.api;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

import io.github.makbn.core.exception.InvalidRequestException;

/**
 * type of post shows if its from which provider
 */
public enum PostType {
    TWITTER("https://twitter/"), CNN("https://cnn.com"), ALL("*");


    private String host;

    PostType(String host) {
        this.host = host;
    }

    public static void checkTypeName(String name) {
        try {
            PostType.valueOf(name);
        } catch (Exception wrongName) {
            throw new InvalidRequestException("type value is wrong!");
        }

    }

    public String getHost() {
        return host;
    }
}
