import dao.DatabaseFactory
import io.javalin.Javalin
import kotlinx.coroutines.runBlocking
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

    app.get("/testInput") {
        ctx ->
        runBlocking {
            ArticleRepository().addTestArticle()
            ctx.result("Success")
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
