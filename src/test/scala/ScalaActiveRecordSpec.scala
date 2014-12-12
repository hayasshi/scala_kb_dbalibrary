import java.sql.Timestamp

import org.scalatest.BeforeAndAfterAll

import ScalaActiveRecordSample._

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

class ScalaActiveRecordSpec extends BaseSpec with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    // Squeryl initialize(table create)
    Tables.initialize
  }

  override def afterAll(): Unit = {
    // Squeryl cleanup
    Tables.cleanup
  }

//  testWithTime("create") {
//  }

  testWithTime("insert") {
    (1 to 1000).foreach { i =>
      User(s"テスト${i}", Some(new Timestamp(System.currentTimeMillis()))).save()
    }
  }

  testWithTime("update") {
//     User.where(_.id >= 500L).toList foreach { user =>
    (500 to 1000).foreach { i =>
      User.find(i).foreach { user =>
        user.lastLogin = Some(new Timestamp(System.currentTimeMillis))
        user.save()
      }
    }
  }

  testWithTime("select") {
    println(User.find(3))
  }

  testWithTime("delete") {
    User.find(100).foreach(_.delete())
  }

}