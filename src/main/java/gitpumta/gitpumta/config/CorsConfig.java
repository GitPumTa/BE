package gitpumta.gitpumta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 개발용: 모든 도메인 허용, 배포 땐 실제 앱 도메인으로 제한 권장
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}