package ubiquitous.backend

import java.nio.file.{Files, Paths}

import org.json4s.native.Serialization.read
import ubiquitous.backend.translation.Lang

object Config {
  def load(from: String): Config = {
    try {
      val cfgString = new String(Files.readAllBytes(Paths.get(from)), charset)
      read[Config](cfgString)
    } catch {
      case _: java.io.IOException =>
        default
    }
  }

  private val default = Config("words.json", UserSettings(Lang.EN, Lang.RU))
}

case class Config(fileWithWords: String,
                  userSettings: UserSettings) extends CanDump[Config] {
  override protected def whatToDump: Config = this
}
