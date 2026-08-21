package com.tinah.notification.service;
import org.springframework.stereotype.Service;
@Service public class NotificationService {public String send(String recipient,String message){return "Notification queued for "+recipient+": "+message;}}
