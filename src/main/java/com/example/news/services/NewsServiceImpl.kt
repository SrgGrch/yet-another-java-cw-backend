package com.example.news.services

import com.example.news.models.Article
import com.example.news.repositories.NewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * "Прослойка" между слоем с запросами и базой данных
 */
@Service("newsService")
class NewsServiceImpl : NewsService {



    @Autowired // Spring сам инциализирует эту переменную
    lateinit var repository: NewsRepository


    /**
     * Сохранение новости в базу данных
     *
     * @param article новсоть которую надо сохранить
     * @return Article всегда не null
     */
    override fun saveArticle(article: Article): Article {
        return repository.save(article)
    }

    /**
     * Получение новости по id
     *
     * @param id уникальный идентификатор новости
     *
     * @return null если такой новости нет, Article если новость найден
     */
    override fun getArticleById(id: String): Article? {
        val res = repository.findById(id)
        return if (res.isEmpty) null
        else res.get()
    }

    /**
     * Получение всех новостей
     *
     * @return список Article
     */
    override fun getAllArticles(): ArrayList<Article> {
        return repository.findAll() as ArrayList<Article>
    }


    /**
     * Удаление новости по id
     *
     * @param id уникальный идентификатор новости
     */
    override fun deleteArticle(id: String) {
        repository.deleteById(id)
    }

    /**
     * Изменение новости
     *
     * @param article новость которую надо обновить
     *
     * @return новость, всегда не null
     */
    override fun updateArticle(article: Article): Article = repository.save(article)

}
