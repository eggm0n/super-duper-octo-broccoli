package proxy

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.util.Timeout

import scala.concurrent.duration._

trait Routes {

  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[Routes])

  implicit lazy val timeout = Timeout(5.seconds)

  lazy val proxyRoutes: Route =
    pathPrefix("") {
      concat(
        pathEnd {
          concat(
            get {
              complete(proxy)
            })
        })
    }

  def proxy = Http().singleRequest(HttpRequest(uri = "http://simple"))
}
