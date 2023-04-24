import io.javalin.Javalin
import model.Employee

fun main(args: Array<String>) {
    val app = Javalin.create(/*config*/)
        .get("/") { ctx -> ctx.result("Hello World") }
        .start(7070)

    app.get("/output") { ctx ->
        val list = ArrayList<Employee>()
        list.add(Employee("1","Ram"))
        list.add(Employee("2","Kumar"))
        ctx.json(list)
    }

    app.post("/input") { ctx ->
        // some code
        ctx.status(201)
    }
}