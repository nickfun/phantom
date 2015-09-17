package gs.nick.phantom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.*;

class WebApplication {

    public static void main(String[] args) {
        logSystemInfo();
        get("/hello", (req, resp) -> {
            System.out.println("Request:");
            System.out.println(req);
            System.out.println("Response:");
            System.out.println(resp);
            return "Hello, world!";
        });      
    }

    private static void logSystemInfo() {
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
