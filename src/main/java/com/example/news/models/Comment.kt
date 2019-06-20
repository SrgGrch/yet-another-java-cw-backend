package com.example.news.models

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * Модель комментария
 */
class Comment(val text: String = " ", val author: User? = null)