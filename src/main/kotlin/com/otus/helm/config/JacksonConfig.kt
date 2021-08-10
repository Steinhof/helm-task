package com.otus.helm.config

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JacksonConfig {

    @Bean
    fun jacksonMapper() = ObjectMapper().apply {
        registerModule(KotlinModule())
    }
}