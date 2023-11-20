package xyz.weizj.manger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "stu.auth")
public class UserAuthProperties {
    private List<String> noAuthUrls;
}
