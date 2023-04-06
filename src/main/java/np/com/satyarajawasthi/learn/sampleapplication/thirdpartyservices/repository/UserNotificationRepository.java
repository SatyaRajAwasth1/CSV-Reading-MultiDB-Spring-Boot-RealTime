package np.com.satyarajawasthi.learn.sampleapplication.thirdpartyservices.repository;

import np.com.satyarajawasthi.learn.sampleapplication.thirdpartyservices.model.UserNotification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("thirdPartyDataSource")
public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    UserNotification findUserToBeNotifiedByUserName(String userName);
}
