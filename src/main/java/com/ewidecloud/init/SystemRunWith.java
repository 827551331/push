package com.ewidecloud.init;

import org.jboss.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName SystemRunWith
 * @Description SpringApplication启动时运行一次的任务
 * @author wang_yw
 * @Date 2018年8月1日 下午6:00:28
 * @version 1.0.0
 */
@Component
public class SystemRunWith implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(SystemRunWith.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("-->系统启动完成");
    }

}
