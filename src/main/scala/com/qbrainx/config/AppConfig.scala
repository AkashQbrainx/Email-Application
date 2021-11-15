package com.qbrainx.config

import com.typesafe.config.{Config, ConfigFactory}

object AppConfig {
  val producerConfig: Config =ConfigFactory.load().getConfig("akka.kafka.producer")
  val consumerConfig: Config =ConfigFactory.load().getConfig("akka.kafka.consumer")
  val userConfig: Config =ConfigFactory.load().getConfig("mail-authenticator")
  val slickConfig: Config =ConfigFactory.load().getConfig("slick")

}