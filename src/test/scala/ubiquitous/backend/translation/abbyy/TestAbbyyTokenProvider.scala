package ubiquitous.backend.translation
package abbyy

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestAbbyyTokenProvider extends FunSuite {
  test("get new token") {
    assert(new AbbyyTokenProvider().getToken.nonEmpty)
  }
}
