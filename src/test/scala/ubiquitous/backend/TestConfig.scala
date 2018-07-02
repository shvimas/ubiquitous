package ubiquitous.backend

import java.nio.file.{Files, Paths}

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import ubiquitous.backend.translation.Lang

@RunWith(classOf[JUnitRunner])
class TestConfig extends FunSuite {

  private val testResources = "src/test/resources/"
  private val testConfigPath = testResources + "config.json"

  test("load") {
    val cfg = Config.load(testConfigPath)
    val shouldBe = Config("words.json", UserSettings(Lang.EN, Lang.RU))
    assert(cfg == shouldBe)
  }

  test("dump") {
    val shouldBe = Config.load(testConfigPath)
    val tempConfigPath = testResources + "temp_config.json"
    shouldBe.dumpTo(tempConfigPath)
    val loaded = Config.load(tempConfigPath)
    Files.delete(Paths.get(tempConfigPath))
    assert(loaded == shouldBe)
  }
}
