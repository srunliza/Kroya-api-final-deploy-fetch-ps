package com.kshrd.kroya_api.controller;

import com.kshrd.kroya_api.payload.BaseResponse;
import com.kshrd.kroya_api.service.Notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("")
    public BaseResponse<?> getNotification(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return notificationService.getNotification(pageNumber, pageSize);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> DeletedNotification(@PathVariable("id") Long id) {
        return notificationService.deletedNotification(id);
    }

    @PutMapping("/{id}")
    public BaseResponse<?> updateNotificationStatus(@PathVariable("id") Long id) {
        return notificationService.updateNotificationStatus(id);
    }
}
