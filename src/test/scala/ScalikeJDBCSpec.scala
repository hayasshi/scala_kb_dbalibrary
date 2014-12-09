import java.sql.{Connection, DriverManager, Timestamp}

import ScalikeJDBCSample._

import scalikejdbc._

class ScalikeJDBCSpec extends BaseSpec {

  // Connection settings
  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:mem:scalickjdbctest", "sa", "")

  runTest("create") {
    implicit val session = DB.autoCommitSession()
    sql"CREATE TABLE USERS (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME TEXT NOT NULL, LAST_LOGIN TIMESTAMP)".execute.apply()
    session.close()
  }

  runTest("insert") {
    DB.localTx { implicit session =>
      val column = User.column
      (1 to 1000) foreach { i =>
        withSQL {
          insert.into(User).namedValues(column.name -> s"テスト${}i}", column.lastLogin -> None)
        }.updateAndReturnGeneratedKey.apply()
      }
    }
  }

  runTest("update") {
    DB.localTx { implicit session =>
      withSQL {
        val column = User.column
        update(User).set(column.name -> "testes", column.lastLogin -> new Timestamp(System.currentTimeMillis())).where.eq(column.id, 2)
      }.update.apply()
    }
  }

  runTest("select") {
    val u = User.syntax("u")
    DB autoCommit { implicit session =>
      withSQL {
        select.from(User as u).where.eq(u.id, 100)
      }.map(rs => User(rs.long("ID"), rs.string("NAME"), rs.timestampOpt("LAST_LOGIN"))).single.apply()
    }
  }

  runTest("delete") {
    DB.localTx { implicit session =>
      withSQL {
        val column = User.column
        update(User).set(column.name -> "testes", column.lastLogin -> new Timestamp(System.currentTimeMillis())).where.eq(column.id, 2)
      }.update.apply()
    }
  }
}