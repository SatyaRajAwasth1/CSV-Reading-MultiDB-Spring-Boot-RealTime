package np.com.esewa.learn.sampleapplication.thirdpartyservices.resources;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserNotificationRequestDto implements Serializable {
    private String url;
    private String userName;
    private String password;
}
