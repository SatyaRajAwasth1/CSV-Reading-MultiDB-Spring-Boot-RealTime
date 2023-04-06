package np.com.satyarajawasthi.learn.sampleapplication.thirdpartyservices.resources;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NotifyDto implements Serializable {
    private String notificationMessage;
    private Long SUCCESS_COUNT;
    private Long FAIL_COUNT;
    private String csvFileName;
}
