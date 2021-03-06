import java.sql.{Connection, DriverManager, Timestamp}
import org.scalatest._
import org.slf4j._
import scalikejdbc._
import ScalikeJDBCSample._

class ScalikeJDBCSpec extends BaseSpec with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:scalickjdbctest", "sa", "")
  }

  override def afterAll(): Unit = {
    ConnectionPool.closeAll()
  }

  testWithTime("create") {
    implicit val session = AutoSession
    sql"CREATE TABLE IF NOT EXISTS USERS (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME TEXT NOT NULL, LAST_LOGIN TIMESTAMP)".execute.apply()
  }

  testWithTime("insert") {
    DB.localTx { implicit session =>
      val column = User.column
      (1 to 1000) foreach { i =>
        val createdId = withSQL {
          val now = Some(new Timestamp(System.currentTimeMillis))
          insert.into(User).namedValues(column.name -> s"テスト${i}", column.lastLogin -> now)
        }.updateAndReturnGeneratedKey.apply()
      }
    }
  }

  testWithTime("update") {
    DB.localTx { implicit session =>
      withSQL {
        val column = User.column
        update(User).set(column.lastLogin -> new Timestamp(System.currentTimeMillis())).where.gt(column.id, 500)
      }.update.apply()
    }
  }

  testWithTime("select") {
    val u = User.syntax("u")
    DB.autoCommit { implicit session =>
      val target = withSQL {
        select.from(User as u).where.eq(u.id, 100)
      }.map(User(u)).single.apply()
    }
  }

  testWithTime("delete") {
    DB.localTx { implicit session =>
      withSQL {
        val column = User.column
        delete.from(User).where.eq(column.id, 2)
      }.update.apply()
    }
  }
}
