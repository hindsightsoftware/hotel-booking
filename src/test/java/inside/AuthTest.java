package inside;

import auth.Tokens;
import org.approvaltests.Approvals;
import org.junit.Test;

public class AuthTest {

    @Test
    public void testCreateToken(){
        String generatedToken = Tokens.create();

        Approvals.verify(generatedToken.getClass());
    }

    @Test
    public void testConfirmToken(){
        String generatedToken = Tokens.create();
        Boolean isValidToken = Tokens.verify(generatedToken);

        Approvals.verify(isValidToken);
    }

    @Test
    public void testConfirmFalseToken(){
        String generatedToken = "abcdefgh";
        Boolean isNotValidToken = Tokens.verify(generatedToken);

        Approvals.verify(isNotValidToken);
    }
}
