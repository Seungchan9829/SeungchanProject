package ksc.ts.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CustomCorsConfigurationSource implements CorsConfigurationSource {

    private final CorsConfiguration config;

    public CustomCorsConfigurationSource(){
        config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
    }




    @Override
    public org.springframework.web.cors.CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        return config;
    }
}
