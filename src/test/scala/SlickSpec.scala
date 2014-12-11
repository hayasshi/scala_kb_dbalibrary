import SlickSample._
import org.scalatest.BeforeAndAfterAll

import scala.slick.driver.JdbcDriver.simple._

import java.sql.Timestamp

class SlickSpec extends BaseSpec with BeforeAndAfterAll {

  implicit lazy val session = Database.forURL("jdbc:h2:mem:slicktest", driver = "org.h2.Driver").createSession()

  override def afterAll(): Unit = {
    session.close()
  }

  testWithTime("create") {
    SlickSample.query.ddl.create
  }

  testWithTime("insert") {
    (1 to 1000) foreach { i =>
      SlickSample.query.insert(new User(0, "test" + i, Option(new Timestamp(System.currentTimeMillis))))
    }
  }

  testWithTime("update") {
    SlickSample.query.filter(_.id === 1L).update(User(1L, "テスト", Option(new Timestamp(System.currentTimeMillis))))
  }

  testWithTime("select") {
    val u1 = SlickSample.query.filter(_.id === 100L).firstOption
    val u2 = (for (u <- SlickSample.query if u.id === 100L) yield {u}).firstOption
    println(u1, u2)
  }

  testWithTime("delete") {
    SlickSample.query.filter(_.id === 1L).delete
  }

  testWithTime("Plain SQL") {
    import scala.slick.jdbc.StaticQuery.interpolation
    import scala.slick.jdbc.GetResult
    implicit val getUser = GetResult(rs => User(rs.nextLong, rs.nextString, rs.nextTimestampOption))
    println(sql"select * from USERS where ID = 2".as[User].list)
  }

  testWithTime("check SQL") {
    println(SlickSample.query.filter(_.id < 1L).selectStatement)
  }

}