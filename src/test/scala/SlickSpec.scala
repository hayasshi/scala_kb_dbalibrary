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
    MyTables.users.ddl.create
  }

  testWithTime("insert") {
    (1 to 1000) foreach { i =>
      MyTables.users.insert(new User(0, "test" + i, Some(new Timestamp(System.currentTimeMillis))))
    }
  }

  testWithTime("update") {
    MyTables.users.filter(_.id >= 500L).map(_.lastLogin).update(Some(new Timestamp(System.currentTimeMillis)))
  }

  testWithTime("select") {
    val u1 = MyTables.users.filter(_.id === 100L).firstOption
    val ulist = (for (u <- MyTables.users if u.id >= 100L) yield {u}).list
  }

  testWithTime("delete") {
    MyTables.users.filter(_.id === 1L).delete
  }

  testWithTime("Plain SQL") {
    import scala.slick.jdbc.StaticQuery.interpolation
    import scala.slick.jdbc.GetResult
    implicit val getUser = GetResult(rs => User(rs.nextLong, rs.nextString, rs.nextTimestampOption))
    sql"select * from USERS where ID = 2".as[User].list
  }

  testWithTime("check SQL") {
    println(MyTables.users.filter(_.id < 1L).selectStatement)
  }

}