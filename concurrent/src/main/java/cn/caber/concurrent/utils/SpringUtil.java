package cn.caber.concurrent.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

/**
 * Spring上下文工具类，用以让普通类获取Spring容器中的Bean
 * @author leo
 */

@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    /**
     * 获取applicationContext
     */
    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    public static Environment getEnvironment(){
        return getApplicationContext().getEnvironment();
    }

    /**
     * 获取Class<?>的实现类实例Bean
     * @param clazz
     * @return
     */
    public static Map<String, ?> getBeanOfType(Class<?> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }

    /**
     * 获取 Bean
     * @param clazz
     * @return
     */
    public static Object getBean(Class<?> clazz) {
        return getApplicationContext().getBean(clazz);
    }
}
