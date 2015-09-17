/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gs.nick.phantom;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author nfunnell
 */
public class WebApplicationConfig extends Configuration {

    @NotEmpty
    private String TwilioAccountSid;

    @NotEmpty
    private String TwilioAccountSecret;

    @JsonProperty
    public String getTwilioAccountSid() {
        return TwilioAccountSid;
    }

    @JsonProperty
    public void setTwilioAccountSid(String TwilioAccountSid) {
        this.TwilioAccountSid = TwilioAccountSid;
    }

    @JsonProperty
    public String getTwilioAccountSecret() {
        return TwilioAccountSecret;
    }

    @JsonProperty
    public void setTwilioAccountSecret(String TwilioAccountSecret) {
        this.TwilioAccountSecret = TwilioAccountSecret;
    }
}
