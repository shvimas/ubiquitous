package ubiquitous.backend.translation
package abbyy

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestAbbyyTranslator extends FunSuite {
  test("1") {
    val translation = AbbyyTranslator.translate("word", Lang.EN, Lang.RU)
    val shouldBe = "слово"
    assert(translation.get.get == shouldBe)
  }
}
