package io.github.makbn.health;

import com.codahale.metrics.health.HealthCheck;
import io.github.makbn.api.PostType;

import java.util.Arrays;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public class ApplicationHealthCheck extends HealthCheck {

    /**
     * @return results about providers are accessible from server or not
     * @throws Exception
     */
    @Override
    protected Result check() throws Exception {
        Arrays.stream(PostType.values()).forEach(provider -> {

        });

        return Result.healthy();
    }
}
