package com.example.news.repositories

import com.example.news.models.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, String>{
    fun findByLogin(login: String): Optional<User>
}
