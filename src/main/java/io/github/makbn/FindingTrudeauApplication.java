package io.github.makbn;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.github.makbn.health.ApplicationHealthCheck;
import io.github.makbn.resources.PostsResource;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public class FindingTrudeauApplication extends Application<FindingTrudeauConfiguration> {
    private static Application appContext;

    public static void main(final String[] args) throws Exception {
        appContext = new FindingTrudeauApplication();
        appContext.run(args);
    }

    /**
     * @return current instance of application
     */
    public static Application getAppContext() {
        return appContext;
    }

    @Override
    public String getName() {
        return "Finding Trudeau";
    }

    @Override
    public void run(final FindingTrudeauConfiguration configuration,
                    final Environment environment) {

        environment.healthChecks()
                .register("provider-check", new ApplicationHealthCheck());
        environment.jersey()
                .register(new PostsResource());
    }

}
