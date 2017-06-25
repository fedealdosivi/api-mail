package apimail.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fefe on 14/6/2017.
 */
public class LoginResponse {

    @JsonProperty
    String sessionId;

    public LoginResponse() {
    }

    public LoginResponse(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
