package xyz.weizj.manger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import xyz.weizj.common.annotation.EnableLogAspect;
import xyz.weizj.manger.properties.MinioProperties;
import xyz.weizj.manger.properties.UserAuthProperties;

@SpringBootApplication
@EnableLogAspect
@EnableScheduling
@ComponentScan(basePackages = {"xyz.weizj.manger","xyz.weizj.common"})
@EnableConfigurationProperties(value = {UserAuthProperties.class, MinioProperties.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
