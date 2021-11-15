import sbt._

object Dependencies{
  lazy val akkaVersion = "2.6.16"
  lazy val kafkaversion="2.1.1"
  lazy val javaxVersion="1.6.2"
  lazy val sprayJsonVersion="1.3.6"
  lazy val kafkaVersion1= "2.8.0"
  lazy val slickVersion="3.3.3"
  lazy val akkaHttpVersion="10.2.7"
 lazy val typeSafeConfigVersion= "1.4.1"

  lazy val typeSafeConfig = "com.typesafe" % "config" % typeSafeConfigVersion
  lazy val kafka1="com.typesafe.akka" %% "akka-stream-kafka" % kafkaversion
  lazy val akka ="com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val javaxMail="com.sun.mail" % "javax.mail" % javaxVersion
  lazy val  sprayJson="io.spray" %% "spray-json" % sprayJsonVersion
  lazy val kafka="org.apache.kafka" % "kafka-clients" % kafkaVersion1
  lazy val slick= "com.typesafe.slick" %% "slick" % slickVersion
  lazy val akkaHttp="com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val sprayJsonAkka="com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
  def compileDependencies: Seq[ModuleID] =
    Seq(kafka,akka,javaxMail,sprayJson,kafka1,slick,typeSafeConfig,akkaHttp,sprayJsonAkka)


}