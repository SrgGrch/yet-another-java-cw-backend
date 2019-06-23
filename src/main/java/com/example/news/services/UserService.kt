package com.example.news.services

import com.example.news.models.User

interface UserService{
    fun getUserById(id: String): User?
    fun authUser(login: String, pass: String): User?
    fun regUser(user:User): User?
}
