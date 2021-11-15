package com.qbrainx.app

import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.Source
import com.qbrainx.config.AppConfig
import org.apache.kafka.clients.producer.ProducerRecord
import akka.actor.ActorSystem
import org.apache.kafka.common.serialization.StringSerializer
import scala.annotation.tailrec
import scala.io.StdIn
import com.qbrainx.util.App
object ProducerApp extends App{

    val producerSettings =
      ProducerSettings(AppConfig.producerConfig, new StringSerializer, new StringSerializer)

    @tailrec
    def read():Any= {
      val in=StdIn.readLine()
      Source.single(in)
        .map(value => new ProducerRecord[String, String]("scala10",  value))
        .runWith(Producer.plainSink(producerSettings))
      read()
    }
    read()

  }

