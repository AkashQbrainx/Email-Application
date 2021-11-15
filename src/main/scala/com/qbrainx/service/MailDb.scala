package com.qbrainx.service

import com.qbrainx.model.MailDetails

import scala.concurrent.Future

trait MailDb {

  def insert(mailDetails: MailDetails):Future[Int]
}