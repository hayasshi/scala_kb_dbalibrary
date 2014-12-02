import scala.slick.driver.JdbcDriver.simple._
import scala.slick.lifted.Tag

import java.sql.Timestamp

// ORM定義
class Users(tag: Tag) extends Table[(Long, String, Option[Timestamp])](tag, "users") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc) // 主キー設定、オートインクリメント
  def name = column[String]("name")
  def lastLogin = column[Option[Timestamp]]("last_login") // OptionでNull許容表現

  def * = (id, name, lastLogin)
}

// ORM定義 with case class
case class User(id: Long, name: String, lastLogin: Option[Timestamp])
class Userss(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc) // 主キー設定、オートインクリメント
  def name = column[String]("name")
  def lastLogin = column[Option[Timestamp]]("last_login") // OptionでNull許容表現

  def * = (id, name, lastLogin) <> (User.tupled, User.unapply)
}

object SlickSample {

  def main(args: Array[String]): Unit = {
    val db = Database.forURL("jdbc:h2:mem:test1", driver = "org.h2.Driver")
    db withSession { implicit session =>
      val query = TableQuery[Userss]
      query.ddl.create
      //val user: User = query.filter(_.id === 1L).first
      val optUser: Option[User] = query.filter(_.id === 1L).firstOption
    }
  }
}