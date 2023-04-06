package np.com.esewa.learn.sampleapplication.thirdpartyservices.repository;

import np.com.esewa.learn.sampleapplication.thirdpartyservices.model.UserNotification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("thirdPartyDataSource")
public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    UserNotification findUserToBeNotifiedByUserName(String userName);
}
