package np.com.esewa.learn.sampleapplication.thirdpartyservices.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "third-party-services")
public class UserNotification {
    private final String type = "NOTIFY";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String URL;
    private String userName;
    private String userPassword;
    @Column(name = "is_encrypted")
    private boolean isPasswordEncrypted;
}
