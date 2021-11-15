package com.qbrainx.service

import com.qbrainx.config.AppConfig
import java.util.Properties
import javax.mail._
import javax.mail.internet.{InternetAddress, MimeBodyPart, MimeMessage, MimeMultipart}
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}
import scala.util.{Failure, Success, Try}

object EmailService {

  implicit val ec: ExecutionContextExecutor = ExecutionContext.global
  val prop = new Properties()
  prop.put("mail.smtp.host", "smtp.gmail.com")
  prop.put("mail.smtp.port", "587")
  prop.put("mail.smtp.auth", "true")
  prop.put("mail.smtp.starttls.enable", "true")
  val username: String =AppConfig.userConfig.getString("username")
  val password: String =AppConfig.userConfig.getString("password")
  val session: Session = Session.getInstance(prop,
    new Authenticator() {
      override def getPasswordAuthentication: PasswordAuthentication = new PasswordAuthentication(username, password)
    })
  val message = new MimeMessage(session)

  def get(from: String, to: String, subject: String, msg: String): Unit = {

    Try {
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to))
      message.setSubject(subject)
      val mimeBodyPart = new MimeBodyPart()
      mimeBodyPart.setContent(msg, "text/html")
      val multipart = new MimeMultipart()
      multipart.addBodyPart(mimeBodyPart)
      message.setContent(multipart)
      Transport.
        send(message)
    } match {
      case Success(_) => println("done")
      case Failure(exception) => println(exception)
    }
  }
}