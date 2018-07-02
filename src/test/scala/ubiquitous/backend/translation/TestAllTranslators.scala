package ubiquitous.backend
package translation

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestAllTranslators extends FunSuite {
  test(s"${allTranslators.map(_.name)}") {
    val word = "war"
    val from = Lang.EN
    val to = Lang.RU
    allTranslators
      .par
      .foreach(t => println(s"${t.name}: ${t.translate(word, from, to)
        .getOrElse(throw new RuntimeException(t.name))
        .get}"))
  }
}
