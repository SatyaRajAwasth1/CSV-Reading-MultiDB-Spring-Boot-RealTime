package np.com.esewa.learn.sampleapplication.thirdpartyservices.service;

import np.com.esewa.learn.sampleapplication.thirdpartyservices.resources.NotifyDto;
import np.com.esewa.learn.sampleapplication.thirdpartyservices.resources.UserNotificationRequestDto;

public interface UserNotificationService {
    void notify(NotifyDto notifyDto);

    void addUserNoti(UserNotificationRequestDto userNotificationRequestDto);
}
