package com.example.news.services

import com.example.news.models.User
import com.example.news.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * "Прослойка" между слоем с запросами и базой данных
 */
@Service("userService")
class UserServiceImpl : UserService {

    @Autowired // Spring сам инциализирует эту переменную
    lateinit var repository: UserRepository


    /**
     * Получение пользователя по id
     *
     * @param id уникальный идентификатор пользователя
     *
     * @return null если такого пользователя нет, User если пользователь найден
     */
    override fun getUserById(id: String): User? =
            if (repository.findById(id).isEmpty) null
            else {
                repository.findById(id).get()
            }


    /**
     * Функция авторизации
     * @param login
     * @param pass
     *
     * @return при успешной авторизации User, если авторизация не удалась, то вернет null
     */
    override fun authUser(login: String, pass: String): User? {
        val user = repository.findByLogin(login)
        return when {
            user.isEmpty -> null
            user.get().password != pass -> null
            else -> user.get()
        }
    }


    /**
     * Функция авторизации
     * @param user
     *
     * @return при успешной регистрации User, если регистрация не удалась, то вернет null
     */
    override fun regUser(user: User): User? =
            if (repository.findByLogin(user.login).isEmpty) repository.save(user)
            else null
}