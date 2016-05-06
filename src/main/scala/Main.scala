import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor

object Main {
  implicit val system = ActorSystem("example-api")
  implicit val dispatcher = system.dispatcher

  implicit val materializer = ActorMaterializer()
  val api = new Api(new UserService())
  Http().bindAndHandle(api.route, interface = "0.0.0.0", port = 8080)

}

case class User(id: Int, name: String)

class UserService {
  def getUserById(id: Int): Option[User] = {
    id match {
      case 1 => Some(User(1, "A"))
      case _ => None
    }
  }
}

class Api(userService: UserService)(implicit val system: ActorSystem, implicit val executor: ExecutionContextExecutor) extends JsonFormats {
  val route = {
    get {
      path("test") {
        complete {
          val maybeUser = userService.getUserById(1)
          maybeUser match {
            case Some(u) => u
            case None    => "not found"
          }
        }
      }
    }
  }
}