import dao.DAOFacadeImpl
import dao.DatabaseFactory
import io.javalin.Javalin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import model.Article
import model.Articles
import repository.ArticleRepository

fun main(args: Array<String>) {

    val app = Javalin.create { _ ->
        initDatabase()
    }
        .get("/") { ctx -> ctx.result("Hello World") }
        .start(7070)

    app.get("/output") { ctx ->
        runBlocking {
            val list = ArticleRepository().getAllArticles()
            ctx.json(list)
        }
    }

    app.post("/input") { ctx ->
        // some code
        ctx.status(201)
    }
}

fun initDatabase() {
    DatabaseFactory.init()
}
