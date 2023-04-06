package np.com.satyarajawasthi.learn.sampleapplication.thirdpartyservices.service;

import np.com.satyarajawasthi.learn.sampleapplication.thirdpartyservices.resources.NotifyDto;
import np.com.satyarajawasthi.learn.sampleapplication.thirdpartyservices.resources.UserNotificationRequestDto;

public interface UserNotificationService {
    void notify(NotifyDto notifyDto);
    void addUserNoti(UserNotificationRequestDto userNotificationRequestDto);
}
