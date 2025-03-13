package flowmoney.backend.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import flowmoney.backend.domain.user.User;
import flowmoney.backend.service.auth.dtos.LoginServiceInputDTO;
import flowmoney.backend.service.auth.dtos.LoginServiceOutputDTO;
import flowmoney.backend.service.auth.exception.AuthException;
import flowmoney.backend.service.auth.exception.LoginException;
import flowmoney.backend.utils.InstantUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    //Single user of the application
    final User unicUser = User.with("unicuser@gmail.com", "helloworld");
    private final String TOKEN_SECRET = "hellosecret";
    private final String TOKEN_ISSUER = "flow.money";

    public LoginServiceOutputDTO login(final LoginServiceInputDTO input) {
        final var theUser = User.with(input.email(), input.password());

        if (!unicUser.getEmail().equals(theUser.getEmail())
                || !unicUser.getPassword().equals(theUser.getPassword())) {
            throw new LoginException("User or password not found");
        }

        final var aToken = this.createToken(theUser);

        return new LoginServiceOutputDTO(aToken);
    }

    private String createToken(final User theUser){
        try {
            final var anAlgorithm = Algorithm.HMAC256(TOKEN_SECRET);
            final var aToken = JWT.create()
                    .withIssuer(TOKEN_ISSUER)
                    .withSubject(theUser.getEmail())
                    .withExpiresAt(InstantUtils.now().plusSeconds(60 * 60 * 4)) // 4 hours
                    .sign(anAlgorithm);

            return aToken;
        } catch (IllegalArgumentException | JWTCreationException e){
            throw new AuthException(e.getMessage());
        }
    }

    public String validateToken(final String aToken){
        try {
            final var anAlgorithm = Algorithm.HMAC256(TOKEN_SECRET);

            final var aVerifier = JWT.require(anAlgorithm)
                    .withIssuer(TOKEN_ISSUER)
                    .build();
            final var aDecodedToken = aVerifier.verify(aToken);

            final var aSubject = aDecodedToken.getSubject();

            return aSubject;

        } catch (Exception e){
            return "";
        }
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (username.equals(this.unicUser.getUsername())) {
            return this.unicUser;
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
