package stellarburgers.user;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class User {

    private String name;
    private String email;
    private String password;

}
