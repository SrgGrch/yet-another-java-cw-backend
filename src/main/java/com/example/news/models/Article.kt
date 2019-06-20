package com.example.news.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

/**
 * Модель новости
 * Все новости хранятся в таблице news
 */
@Document("news")
data class Article(
        @Id
        var id: String? = null,
        var title: String? = "",
        var subtitle: String? = "",
        var author: User? = null,
        var text: String? = "",
        var comments: ArrayList<Comment>? = null
)