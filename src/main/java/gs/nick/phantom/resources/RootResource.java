package gs.nick.phantom.resources;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nfunnell
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {

    @GET
    public Map<String, String> getRoot() {
        HashMap<String, String> myData = new HashMap<>();
        myData.put("name", "nick");
        myData.put("age", "unknown");
        return myData;
    }
}
