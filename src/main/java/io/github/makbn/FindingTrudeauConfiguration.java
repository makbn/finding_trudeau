package io.github.makbn;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotEmpty;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

public class FindingTrudeauConfiguration extends Configuration {

    @NotEmpty
    private String proxy;

    @JsonProperty
    public String getProxy() {
        return proxy;
    }

    @JsonProperty
    public void setProxy(String proxy) {
        this.proxy = proxy;
    }
}
