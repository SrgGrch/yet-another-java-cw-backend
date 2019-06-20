package com.example.news.services

import com.example.news.models.Article

interface NewsService {
    fun saveArticle(article: Article): Article
    fun getArticleById(id: String): Article?
    fun getAllArticles(): ArrayList<Article>
    fun deleteArticle(id: String)
    fun updateArticle(article: Article): Article
}
