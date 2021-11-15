package com.qbrainx.app

import akka.Done
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.scaladsl.Sink
import com.qbrainx.config.AppConfig
import com.qbrainx.model.Implicits.rootJsonFormat
import com.qbrainx.model.MailDetails
import com.qbrainx.service.EmailService._
import org.apache.kafka.common.serialization.StringDeserializer
import spray.json._
import com.qbrainx.util.App
import scala.concurrent.Future
object ConsumerApp extends App {

  val consumerSettings: ConsumerSettings[String, String] =
    ConsumerSettings(AppConfig.consumerConfig, new StringDeserializer, new StringDeserializer)
  val result: Future[Done] = Consumer
    .plainSource(consumerSettings, Subscriptions.topics("scala10"))
    .map(_.value())
    .runWith(Sink.foreach(json=> {
      println(json)
      sendMail(json)
    }))

  def sendMail(json:String): Unit ={
    val mail: MailDetails =json.parseJson.convertTo[MailDetails]
    get(mail.from,mail.to,mail.subject,mail.message)
  }
}