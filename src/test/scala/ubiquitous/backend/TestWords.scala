package ubiquitous.backend

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestWords extends FunSuite {
  val testResources = "src/test/resources/"

  test("fromFile") {
    val filename = testResources + "words.json"
    val words = Words("word" -> "слово", "arm" -> "рука", "leg" -> "нога")
    words.dumpTo(filename)
    val loaded = Words.load(filename)
    assert(words == loaded)
  }
}
