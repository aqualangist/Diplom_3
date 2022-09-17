package stellarburgers.user;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class UserCredentials {

    private String email;
    private String password;

}
