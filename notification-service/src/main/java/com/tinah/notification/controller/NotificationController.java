package com.tinah.notification.controller;
import com.tinah.notification.service.NotificationService; import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/api/v1/notifications") public class NotificationController {private final NotificationService service; public NotificationController(NotificationService service){this.service=service;}
 @PostMapping public Map<String,String> send(@RequestParam String recipient,@RequestParam String message){return Map.of("status",service.send(recipient,message));}}
