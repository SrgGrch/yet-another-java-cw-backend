package com.example.news.controllers

import com.example.news.models.Article
import com.example.news.models.Comment
import com.example.news.services.NewsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.security.auth.callback.ConfirmationCallback.OK

@RestController
class NewsController {

    @Autowired
    lateinit var service: NewsService


    /**
     * Создание новой новости, принимает запрос по адрссу localhost:3000/news/create
     * @param article новость
     * @return созданная новость, всегда не null
     */
    @PostMapping("/news/create")
    internal fun saveArticle(@RequestBody article: Article): Article {
        return service.saveArticle(article)
    }


    /**
     * Получение новости по id. Запрос по адресу localhost:3000/news/{id}
     * @param id уникальный индентификатор новости
     * @return новость
     */
    @GetMapping("/news/{id}")
    internal fun getArticleById(@PathVariable id: String): ResponseEntity<Article> {
        val article = service.getArticleById(id)
        return if (article == null) ResponseEntity(HttpStatus.NOT_FOUND)
        else  ResponseEntity(article, HttpStatus.OK)
    }

    /**
     * Получение новости по id. Запрос по адресу localhost:3000/news
     * @return список всех новостей
     */
    @GetMapping("/news")
    internal fun getAllArticles() = service.getAllArticles()

    /**
     * Удаление новости по id. Запрос по адресу localhost:3000/news/{id}
     * @param id уникальный индентификатор новости
     * TODO добавить проверку полномочий
     */
    @DeleteMapping("/news/{id}")
    internal fun deleteArticleById(@PathVariable id: String): ResponseEntity<Any> {
        service.deleteArticle(id)
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * Добавление комментраия в новость по id. Запрос по адресу localhost:3000/news/{id}
     * @param id уникальный индентификатор новости
     * @param comment комментарий
     */
    @PostMapping("/news/{id}")
    internal fun addComment(@PathVariable id: String, @RequestBody comment: Comment): ResponseEntity<Article> {

        val article = service.getArticleById(id)
        if (article!!.comments == null) article.comments = ArrayList()
        article.comments!!.add(comment)
        return ResponseEntity(service.updateArticle(article), HttpStatus.OK)
    }

    /**
     * Добавление комментраия в новость по id. Запрос по адресу localhost:3000/news/{id}
     * @param id уникальный индентификатор новости
     * @param comment комментарий
     */
    @PutMapping("/news/{id}")
    internal fun editArticle(@PathVariable id: String, @RequestBody article: Article): ResponseEntity<Article> {

        var oldArticle = service.getArticleById(id)

        return if (oldArticle == null) ResponseEntity(HttpStatus.NOT_FOUND)
        else {

            if (!article.text.isNullOrBlank()) oldArticle.text = article.text
            if (!article.subtitle.isNullOrBlank()) oldArticle.subtitle = article.subtitle
            if (!article.title.isNullOrBlank()) oldArticle.title = article.title

            ResponseEntity (service.saveArticle(oldArticle), HttpStatus.OK)
        }

    }


}
