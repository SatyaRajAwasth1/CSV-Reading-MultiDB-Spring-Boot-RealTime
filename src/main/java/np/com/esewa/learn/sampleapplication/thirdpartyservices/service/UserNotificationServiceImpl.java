package np.com.esewa.learn.sampleapplication.thirdpartyservices.service;

import np.com.esewa.learn.sampleapplication.thirdpartyservices.model.UserNotification;
import np.com.esewa.learn.sampleapplication.thirdpartyservices.repository.UserNotificationRepository;
import np.com.esewa.learn.sampleapplication.thirdpartyservices.resources.NotifyDto;
import np.com.esewa.learn.sampleapplication.thirdpartyservices.resources.UserNotificationRequestDto;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class UserNotificationServiceImpl implements UserNotificationService{

    private final UserNotificationRepository userNotificationRepository;

    public UserNotificationServiceImpl(UserNotificationRepository userNotificationRepository) {
        this.userNotificationRepository = userNotificationRepository;
    }

    @Override
    public void notify(NotifyDto notifyDto) {
       List <UserNotification> allUserNotificationDetails = userNotificationRepository.findAll();

       long totalCount = notifyDto.getSUCCESS_COUNT()+notifyDto.getFAIL_COUNT();
       String notificationMessage = "Out of "+totalCount+" CSV data, " +
               ""+notifyDto.getFAIL_COUNT()+" failed and "+notifyDto.getSUCCESS_COUNT()+"" +
               " successfully added for "+notifyDto.getCsvFileName()+" file.";
       notifyDto.setNotificationMessage(notificationMessage);

        for (UserNotification userNotificationDetail : allUserNotificationDetails) {
            String url = userNotificationDetail.getURL();

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<NotifyDto> request = new HttpEntity<>(notifyDto);
            restTemplate.postForEntity(url,request,String.class);
        }

    }

    @Override
    public void addUserNoti(UserNotificationRequestDto userNotificationRequestDto) {
        UserNotification userNotification = new UserNotification();
        userNotification.setUserName(userNotificationRequestDto.getUserName());
        userNotification.setUserPassword(userNotificationRequestDto.getPassword());
        userNotification.setURL(userNotificationRequestDto.getUrl());
        userNotification.setPasswordEncrypted(false);

        userNotificationRepository.save(userNotification);
    }
}
