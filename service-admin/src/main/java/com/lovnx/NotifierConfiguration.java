package com.lovnx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.netflix.governator.annotations.binding.Primary;

import de.codecentric.boot.admin.notify.Notifier;
import de.codecentric.boot.admin.notify.RemindingNotifier;

/**
 * 
 * @Title: 为监控的服务添加邮件通知
 * @Package com.lovnx 
 * @author yezhiyuan   
 * @date 2017年6月14日 上午10:18:13 
 * @version V1.0
 */

@Configuration
@EnableScheduling
public class NotifierConfiguration {
	
    @Autowired
    private Notifier notifier;

    //服务上线或者下线都通知
    private String[] reminderStatuses = { "DOWN", "OFFLINE" };
    
    @Bean
    @Primary
    public RemindingNotifier remindingNotifier() {
        RemindingNotifier remindingNotifier = new RemindingNotifier(notifier);
//        remindingNotifier.setReminderPeriod(TimeUnit.MINUTES.toMillis(5)); 
        remindingNotifier.setReminderStatuses(reminderStatuses);
        return remindingNotifier;
    }

//    @Scheduled(fixedRate = 60_000L) 
//    public void remind() {
//        remindingNotifier().sendReminders();
//    }
}