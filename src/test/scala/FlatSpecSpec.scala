import org.scalatest.{FlatSpec, BeforeAndAfterAll, BeforeAndAfter}

/**
 * Created by daisuke.hayashi on 2014/12/11.
 */
class FlatSpecSpec extends FlatSpec with BeforeAndAfter with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    println("beforeAll")
  }

  override def afterAll(): Unit = {
    println("afterAll")
  }

  before {
    println("before")
  }

  after {
    println("after")
  }

  "FlatSpecSpec" should "test1" in {
    println("test1")
  }

  it should "test2" in {
    println("test2")
  }

  it should "test3" in {
    println("test3")
  }

}
