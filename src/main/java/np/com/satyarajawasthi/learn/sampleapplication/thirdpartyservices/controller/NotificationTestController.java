package np.com.satyarajawasthi.learn.sampleapplication.thirdpartyservices.controller;

import np.com.satyarajawasthi.learn.sampleapplication.thirdpartyservices.resources.UserNotificationRequestDto;
import np.com.satyarajawasthi.learn.sampleapplication.thirdpartyservices.service.UserNotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotificationTestController {

    private final UserNotificationService userNotificationService;

    public NotificationTestController(UserNotificationService userNotificationService) {
        this.userNotificationService = userNotificationService;
    }

    @GetMapping("/test")
    String testNotify(){
        return "hello";
    }

    @PostMapping("notify/add")
    void addUserNotiDetails(@RequestBody UserNotificationRequestDto userNotificationRequestDto){
        userNotificationService.addUserNoti(userNotificationRequestDto);
    }

}
