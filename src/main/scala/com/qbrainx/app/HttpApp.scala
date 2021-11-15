package com.qbrainx.app

import akka.http.scaladsl.Http
import com.qbrainx.app.ConsumerApp.actorSystem.dispatcher
import com.qbrainx.service.route.EmailRoute.route
import scala.concurrent.Future
import scala.util.{Failure, Success}
import com.qbrainx.util.App

object HttpApp extends App {

  val a: Future[Http.ServerBinding] = Http().newServerAt("localhost", 8081).bind(route)
  a.onComplete{
    case Success(value) => println(value)
    case Failure(exception)=>println(exception)
  }

}
