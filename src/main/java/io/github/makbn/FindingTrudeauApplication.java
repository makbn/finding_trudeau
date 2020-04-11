package io.github.makbn;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.github.makbn.core.ApplicationBinder;
import io.github.makbn.core.ApplicationProxy;
import io.github.makbn.health.ApplicationHealthCheck;
import io.github.makbn.resources.PostsResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public class FindingTrudeauApplication extends Application<FindingTrudeauConfiguration> {
    private static Application appContext;

    public static void main(final String[] args) throws Exception {
        appContext = new FindingTrudeauApplication();
        appContext.run("server", "config.yml");
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
    public void initialize(Bootstrap<FindingTrudeauConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/page"));
    }

    @Override
    public void run(final FindingTrudeauConfiguration configuration,
                    final Environment environment) {

        corsConfiguration(environment);

        environment.jersey().register(new ApplicationBinder());
        environment.jersey().register(new ApplicationProxy(configuration));
        environment.healthChecks()
                .register("provider-check", new ApplicationHealthCheck());
        environment.jersey()
                .register(PostsResource.class);
    }

    private void corsConfiguration(Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
