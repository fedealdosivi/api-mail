package apimail.Session;

import apimail.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;


/**
 * Created by fefe on 14/6/2017.
 */
@Service
public class SessionData {
    final static Logger logger = LoggerFactory.getLogger(SessionData.class);
    HashMap<String, Authentication> sessionData;

    @Value("${session.expiration}")
    int expirationTime;

    @Autowired
    Authentication aData;

    public SessionData() {
        try {
            this.sessionData = new HashMap<String, Authentication>();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public String addSession(Usuario usuario) {
        try {
            String sessionId = UUID.randomUUID().toString();
            aData.setUsuario(usuario);
            aData.setLastAction(LocalDateTime.now());
            this.sessionData.put(sessionId, aData);
            return sessionId;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }


    public void removeSession(String sessionId) {
        try {
            sessionData.remove(sessionId);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public Authentication getSession(String sessionId) {
        try {
            aData = this.sessionData.get(sessionId);
            if (aData != null) {
                return aData;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    @Scheduled(fixedRate = 5000)
    public void checkSessions() {
        try {
            System.out.println("Checking sessions");
            Set<String> sessionsId = this.sessionData.keySet();
            for (String sessionId : sessionsId) {
                aData = this.sessionData.get(sessionId);
                if (aData.getLastAction().plusSeconds(expirationTime).isBefore(LocalDateTime.now())) {
                    System.out.println("Deleting sessionId = " + sessionId);
                    this.sessionData.remove(sessionId);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
