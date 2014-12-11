import java.sql.Timestamp

import SkinnyORMSample._
import scalikejdbc._

class SkinnyORMSpec extends BaseSpec {

  // Connection settings(ScalikeJDBCSpecと同じコネクションが使われているっぽい)
  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:mem:skinnyormtest", "sa", "")

  runTest("create") {
    implicit val session = DB.autoCommitSession()
    sql"CREATE TABLE IF NOT EXISTS USERS (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME TEXT NOT NULL, LAST_LOGIN TIMESTAMP)".execute.apply()
    session.close()
  }

  runTest("insert") {
    (1 to 1000).foreach { i =>
      User.createWithAttributes('name -> ("テスト" + i), 'last_login -> new Timestamp(System.currentTimeMillis()))
    }
  }

  runTest("update") {
    User.updateById(2).withAttributes('name -> "test")
  }

  runTest("select") {
    println(User.findById(100))
  }

  runTest("delete") {
    User.deleteById(2)
  }
}