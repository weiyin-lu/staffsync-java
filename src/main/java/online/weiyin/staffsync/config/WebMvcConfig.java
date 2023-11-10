package online.weiyin.staffsync.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import online.weiyin.staffsync.interceptor.CORSInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfig
 * @Description webmvc配置嘞
 * @Version 1.0.0
 * @Time 2023/11/05 下午 08:03
 * @Author 卢子昂
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        跨域拦截
        registry.addInterceptor(new CORSInterceptor()).addPathPatterns("/**");
//        Sa-Token异常拦截（注解）
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }
}
