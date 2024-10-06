package jp.kkikuchi582.springthymeleafhtmx.configuration

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TemplateConfiguration {
    @Bean
    fun layoutDialect(): LayoutDialect {
        return LayoutDialect()
    }
}