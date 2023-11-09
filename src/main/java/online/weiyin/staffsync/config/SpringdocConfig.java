package online.weiyin.staffsync.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SpringdocConfig
 * @Description springdoc接口文档ui配置类
 * @Version 1.0.0
 * @Time 2023/11/06 下午 03:21
 * @Author 卢子昂
 */
@Configuration
public class SpringdocConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("staffSync人事管理系统 接口文档")
                        .description("staffSync人事管理系统 接口文档")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("weiyin lu")
                                .email("weiyin2002@outlook.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("github")
                        .url("https://github.com/weiyin-lu"));
    }
}
