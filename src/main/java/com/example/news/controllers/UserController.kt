package com.example.news.controllers

import com.example.news.models.User
import com.example.news.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired // Spring сам инциализирует эту переменную
    lateinit var service: UserService


    /**
     * Функция которая отдает пользователя по его id GET запрос по адресу localhost:3000/users/{id}
     *
     * @param id уникальный идентификатор пользователя
     * @return пользователь
      */
    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<User> {
        val user = service.getUserById(id)
        return if (user == null) ResponseEntity(HttpStatus.NOT_FOUND)
        else ResponseEntity(user, HttpStatus.OK)
    }


    /**
     * Функция авторизации id POST запрос по адресу localhost:3000/users
     *
     * @param login логин пользователя
     * @param pass пароль пользователя
     *
     * @return код 200 и пользователь в случае удачной авторизации и 401 если пароль или логин неправильный
     */
    @PostMapping("/users")
    fun authUser(
            @RequestParam("login") login: String,
            @RequestParam("pass") pass: String
    ): ResponseEntity<User> {
        val user = service.authUser(login, pass)
        return if (user == null) ResponseEntity(HttpStatus.UNAUTHORIZED)
        else ResponseEntity(user, HttpStatus.OK)
    }

    /**
     * Функция регистрации POST запрос по адресу localhost:3000/reg
     *
     * @param user модель пользователя
     *
     * @return код 200 и пользователь в случае удачной ргеистрации  и 418 если пользователь с таким логином уже сузествует
     */
    @PostMapping("/reg")
    fun regUser(@RequestBody user: User) =
            if (service.regUser(user) == null) ResponseEntity(HttpStatus.I_AM_A_TEAPOT)
            else ResponseEntity(user, HttpStatus.OK)
}