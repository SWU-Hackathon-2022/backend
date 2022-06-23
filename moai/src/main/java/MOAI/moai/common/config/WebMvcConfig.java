package MOAI.moai.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  정적 리소스 핸들링 커스터마이징
 *  1. member 프로필 사진 URL
 *  2. music 썸네일 사진 URL
 *  3. music 파일 URL
 */
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
