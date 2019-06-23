package com.example.news.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Модель пользователя
 * Все пользователи хранятся в таблице user
 */
@Document("users")
class User(
        @Id var id: String? = null,
        var login: String = "",
        var password: String = "",
        var isRoot: Boolean = false //флаг указывающий на то, является ли пользователь администратором
)