import java.sql.Timestamp

import SkinnyORMSample._
import org.scalatest.BeforeAndAfterAll
import scalikejdbc._

class SkinnyORMSpec extends BaseSpec with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:skinnyormtest", "sa", "")
  }

  override def afterAll(): Unit = {
    ConnectionPool.closeAll()
  }

  testWithTime("create") {
    implicit val session = DB.autoCommitSession()
    sql"CREATE TABLE IF NOT EXISTS USERS (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME TEXT NOT NULL, LAST_LOGIN TIMESTAMP)".execute.apply()
    session.close()
  }

  testWithTime("insert") {
    (1 to 1000).foreach { i =>
      User.createWithAttributes('name -> ("テスト" + i), 'last_login -> new Timestamp(System.currentTimeMillis()))
    }
  }

  testWithTime("update") {
    User.updateBy(sqls.gt(User.column.id, 500)).withAttributes('last_login -> new Timestamp(System.currentTimeMillis))
  }

  testWithTime("select") {
    println(User.findById(100))
  }

  testWithTime("delete") {
    User.deleteById(2)
  }

}