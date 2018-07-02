package ubiquitous.backend

import java.nio.file.{Files, Paths}

import org.json4s.native.Serialization.read

object Config {
  def load(from: String): Config = {
    val cfgString = new String(Files.readAllBytes(Paths.get(from)), charset)
    read[Config](cfgString)
  }
}

case class Config(fileWithWords: String,
                  userSettings: UserSettings) extends CanDump[Config] {
  override protected def whatToDump: Config = this
}
