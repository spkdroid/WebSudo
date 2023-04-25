package repository

import dao.DAOFacadeImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.Article

class ArticleRepository {

    companion object {
        var Dao:DAOFacadeImpl = DAOFacadeImpl()
    }
    suspend fun getAllArticles(): List<Article> {
        return withContext(Dispatchers.IO) {
            Dao.allArticles()
        }
    }

    suspend fun addTestArticle() {
        Dao.addNewArticle("Hello","World");
        Dao.addNewArticle("Hello","World");
    }


}