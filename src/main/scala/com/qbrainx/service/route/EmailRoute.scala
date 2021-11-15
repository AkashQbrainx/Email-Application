package com.qbrainx.service.route

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import akka.http.scaladsl.server.Route
import com.qbrainx.model.Implicits.rootJsonFormat
import com.qbrainx.model.MailDetails
import com.qbrainx.service.EmailService._

object EmailRoute extends App {

  implicit val system: ActorSystem = ActorSystem("mail")

  def route: Route = {
    post {
      path("json") {
        entity(as[MailDetails]) {
          mail => {
            get(mail.from, mail.to, mail.subject, mail.message)
          }
            complete("sent")
        }
      }
    }
  }
}