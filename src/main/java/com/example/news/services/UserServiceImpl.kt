package com.example.news.services

import com.example.news.models.User
import com.example.news.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("userService")
class UserServiceImpl : UserService {

    @Autowired
    lateinit var repository: UserRepository

    override fun getUserById(id: String): User? =
            if (repository.findById(id).isEmpty) null
            else {
                repository.findById(id).get()
            }


    override fun authUser(login: String, pass: String): User? {
        val user = repository.findByLogin(login)
        return when {
            user.isEmpty -> null
            user.get().password != pass -> null
            else -> user.get()
        }
    }


    override fun regUser(user: User): User? =
            if (repository.findByLogin(user.login).isEmpty) repository.save(user)
            else null
}