import org.scalatest.FunSuite
import org.slf4j._

abstract class BaseSpec extends FunSuite {
  lazy val logger = LoggerFactory.getLogger(this.getClass)

  def testWithTime(title: String)(process: => Unit) {
    test(title) {
      val start = System.currentTimeMillis
      process
      val end = System.currentTimeMillis
      logger.info(this.getClass.getSimpleName + " - " + title + ": " + (end - start) + " ms")
    }
  }
}
