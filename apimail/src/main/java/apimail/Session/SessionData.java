package apimail.Session;

import apimail.Model.Usuario;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;


/**
 * Created by fefe on 14/6/2017.
 */
@Service
public class SessionData {
    final static Logger logger = Logger.getLogger(SessionData.class);
    HashMap<String, Authentication> sessionData;

    //@Value("${session.expiration}")
    int expirationTime=300000;


    public SessionData() {
        this.sessionData = new HashMap<String, Authentication>();
    }

    public String addSession(Usuario usuario) {
        String sessionId = UUID.randomUUID().toString();
        Authentication aData = new Authentication();
        aData.setUsuario(usuario);
        aData.setLastAction(new DateTime());
        this.sessionData.put(sessionId, aData);
        return sessionId;
    }


    public void removeSession(String sessionId) {
        sessionData.remove(sessionId);
    }

    public Authentication getSession(String sessionId) {
        Authentication aData = this.sessionData.get(sessionId);
        if (aData != null) {
            return aData;
        } else {
            return null;
        }
    }

    @Scheduled(fixedRate = 5000)
    public void checkSessions() {
        System.out.println("Checking sessions");
        Set<String> sessionsId = this.sessionData.keySet();
        for (String sessionId : sessionsId) {
            Authentication aData = this.sessionData.get(sessionId);
            if (aData.getLastAction().plusSeconds(expirationTime).isBefore(System.currentTimeMillis())) {
                System.out.println("Deleting sessionId = " + sessionId);
                this.sessionData.remove(sessionId);
            }
        }
    }

}
