import java.sql.{Connection, DriverManager, Timestamp}

import ScalikeJDBCSample._
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, BeforeAndAfter}

import scalikejdbc._

class ScalikeJDBCSpec extends BaseSpec with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:scalickjdbctest", "sa", "")
  }

  override def afterAll(): Unit =  {
    ConnectionPool.closeAll()
  }

  testWithTime("create") {
    implicit val session = DB.autoCommitSession()
    sql"CREATE TABLE IF NOT EXISTS USERS (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME TEXT NOT NULL, LAST_LOGIN TIMESTAMP)".execute.apply()
    session.close()
  }

  testWithTime("insert") {
    DB.localTx { implicit session =>
      val column = User.column
      (1 to 1000) foreach { i =>
        withSQL {
          insert.into(User).namedValues(column.name -> s"テスト${i}", column.lastLogin -> None)
        }.updateAndReturnGeneratedKey.apply()
      }
    }
  }

  testWithTime("update") {
    DB.localTx { implicit session =>
      withSQL {
        val column = User.column
        update(User).set(column.name -> "testes", column.lastLogin -> new Timestamp(System.currentTimeMillis())).where.eq(column.id, 2)
      }.update.apply()
    }
  }

  testWithTime("select") {
    val u = User.syntax("u")
    DB autoCommit { implicit session =>
      val target = withSQL {
        select.from(User as u).where.eq(u.id, 100)
      }.map(rs => User(rs.long("ID"), rs.string("NAME"), rs.timestampOpt("LAST_LOGIN"))).single.apply()
      println(target)
    }
  }

  testWithTime("delete") {
    DB.localTx { implicit session =>
      withSQL {
        val column = User.column
        update(User).set(column.name -> "testes", column.lastLogin -> new Timestamp(System.currentTimeMillis())).where.eq(column.id, 2)
      }.update.apply()
    }
  }
}