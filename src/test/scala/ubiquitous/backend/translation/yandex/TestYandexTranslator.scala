package ubiquitous.backend.translation
package yandex

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestYandexTranslator extends FunSuite {
  test("1") {
    val (from, to) = (Lang.EN, Lang.RU)
    val translation = YandexTranslator.translate("hot", from, to)
    val shouldBe = Some(YandexTranslationResult(200, s"${from.yandexId}-${to.yandexId}", List("горячая")))
    assert(translation == shouldBe)
  }
}
