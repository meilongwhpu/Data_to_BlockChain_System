package io.nuls.data;

import io.nuls.common.optimistic.EnableOptimisticLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动类
 *
 * @author Long
 * @date 2020/03/16
 */
@SpringBootApplication
@EnableOptimisticLock
public class DatatochainApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DatatochainApp.class);
        app.run(args);
    }

    /**
     * 兼容tomcat部署模式
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DatatochainApp.class);
    }
}

