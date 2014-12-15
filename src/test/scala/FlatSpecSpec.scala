import org.scalatest._
import org.slf4j._

class FlatSpecSpec extends FlatSpec with BeforeAndAfter with BeforeAndAfterAll {

  lazy val logger = LoggerFactory.getLogger(this.getClass)

  override def beforeAll(): Unit = {
    logger.info("beforeAll")
  }

  override def afterAll(): Unit = {
    logger.info("afterAll")
  }

  before {
    logger.info("before")
  }

  after {
    logger.info("after")
  }

  "FlatSpecSpec" should "test1" in {
    logger.info("test1")
  }

  it should "test2" in {
    logger.info("test2")
  }

  it should "test3" in {
    logger.info("test3")
  }

}
