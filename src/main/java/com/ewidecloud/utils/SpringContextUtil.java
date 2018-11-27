package com.ewidecloud.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description Spring上下文工具类
 * @author xiakun
 * @date 2016年12月27日 下午6:15:40
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /** @Fields applicationContext: Spring应用上下文环境 */
    private static ApplicationContext applicationContext;

    @Override
    public final void setApplicationContext(ApplicationContext paramApplicationContext) throws BeansException {
        this.applicationContext = paramApplicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

}
