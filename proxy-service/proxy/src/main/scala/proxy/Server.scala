package proxy

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Server extends App with Routes {

  implicit val system: ActorSystem = ActorSystem("proxy-service")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  lazy val routes: Route = proxyRoutes

  Http().bindAndHandle(routes, "0.0.0.0", 8080)

  println(s"Server online at http://0.0.0.0:8080/")
  println(s"Testing incremental build")

  Await.result(system.whenTerminated, Duration.Inf)
}
