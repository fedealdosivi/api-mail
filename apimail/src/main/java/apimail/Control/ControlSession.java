package apimail.Control;

import apimail.Model.Usuario;
import apimail.Response.LoginResponse;
import apimail.Services.UserService;
import apimail.Session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fefe on 14/6/2017.
 */
@Controller
public class ControlSession {

    @Autowired
    UserService userService;

    @Autowired
    private SessionData sessionData;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<LoginResponse> getById(@RequestHeader("email") String email, @RequestHeader("password") String password) {
        try {
            Usuario u = userService.login(email, password);
            if (null != u) {
                String sessionId = sessionData.addSession(u);
                return new ResponseEntity<LoginResponse>(new LoginResponse(sessionId), HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping("/logout")
    public
    @ResponseBody
    ResponseEntity getById(@RequestHeader("sessionid") String sessionId) {
        try {
            sessionData.removeSession(sessionId);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
