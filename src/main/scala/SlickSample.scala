import scala.slick.driver.JdbcDriver.simple._
import scala.slick.lifted.Tag

import java.sql.Timestamp

object SlickSample {
  // ORM定義
  class Users(tag: Tag) extends Table[(Long, String, Option[Timestamp])](tag, "USERS") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc) // 主キー設定、オートインクリメント
    def name = column[String]("NAME")
    def lastLogin = column[Option[Timestamp]]("LAST_LOGIN") // OptionでNull許容表現

    def * = (id, name, lastLogin)
  }

  // ORM定義 with case class
  case class User(id: Long, name: String, lastLogin: Option[Timestamp])
  class Userss(tag: Tag) extends Table[User](tag, "USERS") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc) // 主キー設定、オートインクリメント
    def name = column[String]("NAME")
    def lastLogin = column[Option[Timestamp]]("LAST_LOGIN") // OptionでNull許容表現

    def * = (id, name, lastLogin) <> (User.tupled, User.unapply)
  }

  def query = TableQuery[Userss]
}