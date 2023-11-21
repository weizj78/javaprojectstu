package xyz.weizj.manger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import xyz.weizj.manger.properties.MinioProperties;
import xyz.weizj.manger.properties.UserAuthProperties;

@SpringBootApplication
@ComponentScan(basePackages = {"xyz.weizj.manger","xyz.weizj.common"})
@EnableConfigurationProperties(value = {UserAuthProperties.class, MinioProperties.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
