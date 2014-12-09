import org.scalatest.FunSuite

abstract class BaseSpec extends FunSuite {
  def runTest(title: String)(process: => Unit) {
    test(title) {
      val start = System.currentTimeMillis
      process
      val end = System.currentTimeMillis
      println(this.getClass.getSimpleName + " - " + title + ": " + (end - start) + " ms")
    }
  }
}