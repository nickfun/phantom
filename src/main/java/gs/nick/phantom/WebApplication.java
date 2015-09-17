package gs.nick.phantom;

import gs.nick.phantom.healthchecks.NullCheck;
import gs.nick.phantom.resources.RootResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nfunnell
 */
public class WebApplication extends Application<WebApplicationConfig> {

    public static void main(String[] args) throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        // dropwizard happens
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
    public void run(WebApplicationConfig config, Environment e) {
        logSystemInfo();
        e.healthChecks().register("Example", new NullCheck());
        e.jersey().register(new RootResource());
    }

    private void logSystemInfo() {
        Logger log = LoggerFactory.getLogger(WebApplication.class);
        log.info("---------- System Info:");
        log.info("java.version = " + System.getProperty("java.version"));
        log.info("java.vm.name = " + System.getProperty("java.vm.name"));
        log.info("java.vendor = " + System.getProperty("java.vendor"));
        log.info("java.class.path = " + System.getProperty("java.class.path"));
        log.info("os.name = " + System.getProperty("os.name"));
        log.info("os.arch = " + System.getProperty("os.arch"));
        log.info("os.version = " + System.getProperty("os.version"));
        log.info("PID (guess) = " + java.lang.management.ManagementFactory.getRuntimeMXBean().getName());
        log.info("---------- End System Info");

    }

}
