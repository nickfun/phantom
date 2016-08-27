package gs.nick.phantom;

import com.google.gson.Gson;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.list.IncomingPhoneNumberList;
import com.twilio.sdk.resource.instance.IncomingPhoneNumber;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.*;

class WebApplication {

    private TwilioRestClient twilio;
    private Gson gson;
    private Logger log;
    private String publicHost;

    public static void main(String[] args) {
        WebApplication app = new WebApplication();
        app.init();
        app.router();
    }

    private void init() {
        // Log setup
        this.log = LoggerFactory.getLogger(WebApplication.class);

        // Gson setup
        this.gson = new Gson();

        // addressable name on the 'net
        this.publicHost = System.getenv("PUBLIC_HOST");

	// where are static files?
	staticFileLocation("/static");

        // Twilio setup
        String SID = System.getenv("TWILIO_ACCOUNT_SID");
        String SECRET = System.getenv("TWILIO_ACCOUNT_SECRET");
        this.twilio = new TwilioRestClient(SID, SECRET);

        // Port Setup
        String port = System.getenv("PORT");
        int portNumber = 7070;
        if (!(port == null || port.length() == 0)) {
            portNumber = Integer.parseInt(port);
        }
        port(portNumber);

        // Useful Debugging info
        this.logSystemInfo();
    }

    private void router() {
        get("/", (request, response) -> {
            return "Root\n";
        });

        get("/numbers", (request, resposne) -> {
            return gson.toJson(this.getPhoneNumberList());
        });

        /**
         * Make a request to twilio to call myNum from twilioNum when the call connects, it should get twilml from /connect?target=TARGETNUM
         */
        get("/start", (request, response) -> {
            String twilioNum = request.queryParams("twilioNum");
            String targetNum = request.queryParams("targetNum");
            String myNum = request.queryParams("myNum");
            CallFactory callFactory = twilio.getAccount().getCallFactory();
            Map<String, String> callParams = new HashMap<>();
            callParams.put("To", myNum);
            callParams.put("From", twilioNum);
            callParams.put("Url", this.publicHost + "/connect?num=" + targetNum);
            callParams.put("Method", "GET");
            Call call = callFactory.create(callParams);
            return "{\"call_sid\": " + call.getSid() + "}";
        });

        get("/connect", (request, response) -> {
            String num = request.queryParams("num");
            return getCallTwiml(num);
        });
    }

    private String getCallTwiml(String number) {
        return String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<Response>\n"
                + "    <Dial timeout=\"10\">%s</Dial>\n"
                + "</Response>", number);
    }

    private List<String> getPhoneNumberList() {
        List<String> numbers = new ArrayList<>();
        IncomingPhoneNumberList nlist = new IncomingPhoneNumberList(twilio);
        nlist.setRequestAccountSid(twilio.getAccount().getSid());
        for (IncomingPhoneNumber num : nlist) {
            numbers.add(num.getPhoneNumber());
        }
        return numbers;
    }

    private void logSystemInfo() {
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
