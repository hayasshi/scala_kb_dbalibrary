import java.sql.Timestamp

import org.scalatest.BeforeAndAfterAll

import ScalaActiveRecordSample._

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
      User(s"テスト${i}", Option(new Timestamp(System.currentTimeMillis()))).save()
    }
  }

  testWithTime("update") {
    val updated = User.find(2).getOrElse(User("test", Option(new Timestamp(System.currentTimeMillis()))))
    updated.name = "test"
    updated.save()
  }

  testWithTime("select") {
    println(User.find(3))
  }

  testWithTime("delete") {
    User.find(100).foreach(_.delete())
  }

}