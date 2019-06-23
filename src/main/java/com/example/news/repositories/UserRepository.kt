package com.example.news.repositories

import com.example.news.models.User
import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 * Интерфейс для работы с базой данных, CRUD - реализует все стандартные операции, такие как
 * Создание, получение, изменение, удаление и др.
 *
 * Таблица user
 */
interface UserRepository : CrudRepository<User, String>{
    fun findByLogin(login: String): Optional<User>
}
