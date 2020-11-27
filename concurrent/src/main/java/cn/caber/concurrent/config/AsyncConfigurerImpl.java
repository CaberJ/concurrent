package cn.caber.concurrent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfigurerImpl implements AsyncConfigurer {
    @Value("${async.task.poolSize}")
    private Integer taskPoolSize;

    @Value("${async.task.aliveSeconds}")
    private Integer taskAliveSeconds;

    @Value("${async.task.maxPoolSize}")
    private Integer taskMaxPoolSize;

    @Bean(name = "qibotAsyncExecutor")
    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(taskPoolSize == null ? 10 : taskPoolSize);
        threadPoolTaskExecutor.setKeepAliveSeconds(taskAliveSeconds == null ? 30 : taskAliveSeconds);
        threadPoolTaskExecutor.setMaxPoolSize(taskMaxPoolSize == null ? 30 :taskMaxPoolSize);
        threadPoolTaskExecutor.setThreadNamePrefix("qibot_async_task_");
        return threadPoolTaskExecutor;
    }
}
