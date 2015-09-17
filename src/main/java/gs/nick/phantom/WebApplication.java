package gs.nick.phantom;

import gs.nick.phantom.healthchecks.NullCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 *
 * @author nfunnell
 */
public class WebApplication extends Application<WebApplicationConfig> {

    public static void main(String[] args) throws Exception {
        new WebApplication().run(args);
    }

    @Override
    public String getName() {
        return "phantom";
    }

    @Override
    public void initialize(Bootstrap<WebApplicationConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(WebApplicationConfig configuration,
            Environment environment) {
    
        environment.healthChecks().register("Example", new NullCheck());
    }

}
