package com.goofy.event.config.web

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.utils.SpringDocUtils
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.result.view.RequestContext
import org.springframework.web.server.WebSession

/**
 * **Swagger**
 *
 * Provide detailed explanations based on comments.
 *
 * [Local Swagger UI](http://localhost:8080/webjars/swagger-ui/index.html)
 */
@Configuration
class SpringDocConfig(
    private val buildProperties: BuildProperties,
) {
    init {
        SpringDocUtils
            .getConfig()
            .addRequestWrapperToIgnore(
                WebSession::class.java,
                RequestContext::class.java
            )
    }

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .addServersItem(Server().url("/"))
            .info(
                Info()
                    .title(buildProperties.name)
                    .version(buildProperties.version)
                    .description("Susu Rest API Docs")
            )
    }
}
