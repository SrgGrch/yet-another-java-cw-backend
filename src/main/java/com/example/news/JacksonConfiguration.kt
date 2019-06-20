package com.example.news

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class JacksonConfiguration : WebMvcConfigurerAdapter() {

    override fun extendMessageConverters(converters: List<HttpMessageConverter<*>>) {
        for (httpConverter in converters) {
            if (httpConverter is MappingJackson2HttpMessageConverter) {

                val mapper = ObjectMapper().registerModule(KotlinModule())
//                mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
//                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                httpConverter.objectMapper = mapper
            }
        }
    }
}
