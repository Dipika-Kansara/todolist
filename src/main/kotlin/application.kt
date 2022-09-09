import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import org.litote.kmongo.KMongo
import routes.initListRoute

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
fun Application.start() {
    install(CORS) {
        allowHost("localhost:3000")
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)
    }
    install(ContentNegotiation) {
        json()
    }

    routing {
        val client = KMongo.createClient()
        val databaseName = application.environment.config.config("mongoDb").property("database").getString()
        val database = client.getDatabase(databaseName)

        initListRoute(database)
    }
}
