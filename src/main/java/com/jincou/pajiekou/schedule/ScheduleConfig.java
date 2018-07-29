
package com.jincou.pajiekou.schedule;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author xub
 * @since 2018/6/26
 */
@EnableScheduling
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10,
                new ThreadFactoryBuilder().setDaemon(true).setNameFormat("schedule-thread-%d").build());
    }

}
