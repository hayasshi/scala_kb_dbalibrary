import scala.slick.driver.JdbcDriver.simple._

import java.sql.Timestamp

class SlickSpec extends BaseSpec {

  implicit val session = Database.forURL("jdbc:h2:mem:test", driver = "org.h2.Driver").createSession()

  runTest("create") {
    SlickSample.query.ddl.create
  }

  runTest("insert") {
    (1 to 1000) foreach { i =>
      SlickSample.query.insert(new User(0, "test" + i, Option(new Timestamp(System.currentTimeMillis))))
    }
  }

  runTest("update") {
    SlickSample.query.filter(_.id === 1L).update(User(1L, "テスト", Option(new Timestamp(System.currentTimeMillis))))
  }

  runTest("delete") {
    SlickSample.query.filter(_.id === 1L).delete
  }

  runTest("Plain SQL") {
    import scala.slick.jdbc.StaticQuery.interpolation
    import scala.slick.jdbc.GetResult
    implicit val getUser = GetResult(rs => User(rs.nextLong, rs.nextString, rs.nextTimestampOption))
    println(sql"select * from USERS where ID = 2".as[User].list)
  }

  runTest("check SQL") {
    println(SlickSample.query.filter(_.id < 1L).selectStatement)
  }

  session.close
}