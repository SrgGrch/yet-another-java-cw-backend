package com.example.news

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

/**
 * Запуск сервера
 */
fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}


