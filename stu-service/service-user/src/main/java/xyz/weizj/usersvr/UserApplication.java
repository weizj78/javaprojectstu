package xyz.weizj.usersvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import xyz.weizj.common.anno.EnableUserWebMvcConfiguration;

@SpringBootApplication
@EnableUserWebMvcConfiguration
@ComponentScan(basePackages = {"xyz.weizj.common"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
