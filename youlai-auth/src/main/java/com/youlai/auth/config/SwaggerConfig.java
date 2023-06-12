package com.youlai.auth.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 配置
 * <p>
 * Spring Doc FAQ: https://springdoc.org/#faq
 *
 * @author haoxr
 * @since 2023/2/17
 */
@Configuration
public class SwaggerConfig {

    /**
     * 接口信息
     */
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer").bearerFormat("JWT")
                        )
                )
                .info(new Info()
                        .title("认证中心")
                        .version("3.0.0")
                        .description("认证、注销、获取验签公钥接口")
                        .license(new License().name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                );
    }

    /**
     * 认证中心接口
     */
    @Bean
    public GroupedOpenApi authApi() {
        String[] paths = {"/**"};
        String[] packagesToScan = {"com.youlai.auth.controller"};
        return GroupedOpenApi.builder()
                .group("认证中心")
                .packagesToScan(packagesToScan)
                .pathsToMatch(paths)
                .build();
    }

}