package api;

import auth.Tokens;
import model.Auth;
import model.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<Token> createBooking(@RequestBody Auth auth) {
        if(auth.getUsername().equals("admin") && auth.getPassword().equals("password123")){
            return ResponseEntity.ok(new Token(Tokens.create()));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

}
