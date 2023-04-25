package repository

import dao.DAOFacadeImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.Article

class ArticleRepository {

    init {
        Dao = DAOFacadeImpl()
    }

    companion object {
        var Dao:DAOFacadeImpl = TODO()
    }
    suspend fun getAllArticles(): List<Article> {
        return withContext(Dispatchers.IO) {
            Dao.allArticles()
        }
    }
}