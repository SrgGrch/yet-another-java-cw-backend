package com.example.news.services

import com.example.news.models.Article
import com.example.news.repositories.NewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("newsService")
class NewsServiceImpl : NewsService {



    @Autowired
    lateinit var repository: NewsRepository

    override fun saveArticle(article: Article): Article {
        return repository.save(article)
    }

    override fun getArticleById(id: String): Article? {
        val res = repository.findById(id)
        if (res.isEmpty) return null
        else return res.get()
    }

    override fun getAllArticles(): ArrayList<Article> {
        return repository.findAll() as ArrayList<Article>
    }

    override fun deleteArticle(id: String) {
        repository.deleteById(id)
    }

    override fun updateArticle(article: Article): Article = repository.save(article)

}
