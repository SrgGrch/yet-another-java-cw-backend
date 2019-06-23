package com.example.news.repositories

import com.example.news.models.Article
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository

/**
 * Интерфейс для работы с базой данных, CRUD - реализует все стандартные операции, такие как
 * Создание, получение, изменение, удаление и др.
 *
 * Таблица news
 */
interface NewsRepository : CrudRepository<Article, String> {
    fun findOneById(id: String): Article
}
