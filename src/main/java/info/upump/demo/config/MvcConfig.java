package info.upump.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
        .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/bootstrap/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.1.1-1/");
        registry.addResourceHandler("/jquery/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.3.1-1/");
        registry.addResourceHandler("/popper/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/1.16.1/umd/");
    }
}
