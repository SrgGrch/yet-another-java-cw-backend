package com.example.news.controllers

import com.example.news.models.User
import com.example.news.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired
    lateinit var service: UserService

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<User> {
        val user = service.getUserById(id)
        return if (user == null) ResponseEntity(HttpStatus.NOT_FOUND)
        else ResponseEntity(user, HttpStatus.OK)
    }

    @PostMapping("/users")
    fun authUser(
            @RequestParam("login") login: String,
            @RequestParam("pass") pass: String
    ): ResponseEntity<User> {
        val user = service.authUser(login, pass)
        return if (user == null) ResponseEntity(HttpStatus.UNAUTHORIZED)
        else ResponseEntity(user, HttpStatus.OK)
    }

    @PostMapping("/reg")
    fun regUser(@RequestBody user: User) =
            if (service.regUser(user) == null) ResponseEntity(HttpStatus.I_AM_A_TEAPOT)
            else ResponseEntity(user, HttpStatus.OK)
}