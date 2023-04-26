import dao.DatabaseFactory
import io.javalin.Javalin
import kotlinx.coroutines.runBlocking
import opennlp.tools.tokenize.SimpleTokenizer
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

    app.get("/testInput") { ctx ->
        runBlocking {
            ArticleRepository().addTestArticle()
            ctx.result("Success")
        }
    }

    app.post("/input") { ctx ->
        // some code
        ctx.status(201)
    }

    /**
     *  Simple tokenizer
     *
     *  inputString as the token
     *
     *  @Return json response of the string tokens.
     *
     */
    app.post("/text/token") { ctx ->
        val inputString = ctx.formParam("inputString")
        val simpleTokenizer = SimpleTokenizer.INSTANCE
        val tokens: Array<String> = simpleTokenizer
            .tokenize(inputString)
        ctx.json(tokens)
    }


}

fun initDatabase() {
    DatabaseFactory.init()
}
