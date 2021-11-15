package com.qbrainx.service.impl



import com.qbrainx.config
import com.qbrainx.config.SlickConfig
import com.qbrainx.model.MailDetails
import com.qbrainx.service.MailDb
import com.qbrainx.service.impl.MailDbImpl.schema
import slick.lifted.ProvenShape
import scala.concurrent.Future

class MailDbImpl  extends MailDb{
  import config.SlickConfig.jdbcProfile.api._
  val db=SlickConfig.db
  override def insert(mailDetails: MailDetails): Future[Int] =db.run(query+=mailDetails)
  private val query: TableQuery[schema] =TableQuery(new schema(_))

}
object MailDbImpl{

  import SlickConfig.jdbcProfile.api._
  val tableName="MailDetail"
  final class schema(tag:Tag) extends Table[MailDetails](tag,tableName) {

    def from=column[String]("from")
    def to=column[String]("to")
    def uid=column[Int]("uid")
    def subject=column[String]("subject")
    def message=column[String]("message")

    override def * : ProvenShape[MailDetails] = (from,to,uid,subject,message)<>(MailDetails.tupled,MailDetails.unapply)
  }
}
