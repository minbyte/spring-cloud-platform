package com.mgtv.cloud.zuul.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 配置样例：
 * swagger:
 *  api-docs-path:  /v2/api-docs
 *  swagger-version: 2.0
 *  resources:
 *    auth:
 *      name: auth-server
 *      api-docs-path: /auth/v2/api-docs
 *      swagger-version: 2.0
 *
 * swagger:
 *  api-docs-path:  /v2/api-docs
 *  auto-generate-from-zuul-routes: true
 *  ignore-routes: auth-login-token,auth-login-authorize
 */
@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * 配置静态文档地址
     */
    private Map<String, SwaggerResourceProperties> resources = new HashMap<>();

    /**
     * 不显示没有实例的服务
     */
    private Boolean notShowNoInstanceService = true;

    /**
     * Swagger返回JSON文档的接口路径（全局配置）
     */
    private String apiDocsPath = "/v2/api-docs";

    /**
     * Swagger文档版本（全局配置）
     */
    private String swaggerVersion = "2.0";

    /**
     * 为Zuul中的Routes自动生成API文档
     */
    private Boolean autoGenerateFromZuulRoutes = false;

    /**
     * 不自动生成文档的路由名称，设置了ignoreRoutes之后，generateRoutes不生效（需要autoGenerateFromZuulRoutes=true）
     */
    private Set<String> ignoreRoutes = new HashSet<>();

    /**
     * 自动生成文档的路由名称，设置了generateRoutes之后，ignoreRoutes不生效（需要autoGenerateFromZuulRoutes=true）
     */
    private Set<String> generateRoutes = new HashSet<>();

    public boolean needIgnore(String route) {
        if(generateRoutes.size() > 0 && ignoreRoutes.size() > 0) {
            throw new RuntimeException("generateRoutes和ignoreRoutes只能设置一个");
        }

        // generateRoutes和ignoreRoutes都不配置的话，会根据route生成所有的
        if(generateRoutes.size() == 0 && ignoreRoutes.size() == 0 ) {
            return false;
        }

        if(generateRoutes.size() == 0 && ignoreRoutes.contains(route)) {
            return true;
        }

        if(ignoreRoutes.size() == 0 && generateRoutes.contains(route) == false) {
            return true;
        }

        return false;
    }

}
