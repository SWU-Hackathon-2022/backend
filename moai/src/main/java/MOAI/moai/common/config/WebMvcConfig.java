package MOAI.moai.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${memberThumbnailPath}")
    String memberThumbnailPath;

    @Value("${musicThumbnailPath}")
    String musicThumbnailPath;

    @Value("${musicFilePath}")
    String musicFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/member/thumbnail/**").addResourceLocations(memberThumbnailPath);
        registry.addResourceHandler("/music/thumbnail/**").addResourceLocations(musicThumbnailPath);
        registry.addResourceHandler("/music/file/**").addResourceLocations(musicFilePath);
    }

}
