package ubiquitous

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8

import org.json4s.{DefaultFormats, Formats}
import ubiquitous.backend.translation.Lang

import scala.collection.mutable
import scala.language.implicitConversions

package object backend {
  val charset: Charset = UTF_8

  private[this] val dumpDirSysEnvName = "UBIQUITOUS_DUMP_DIR"
  val dumpDir: String = sys.env.getOrElse(dumpDirSysEnvName,
    throw new IllegalArgumentException(s"$dumpDirSysEnvName is not set"))

  implicit val formats: Formats = DefaultFormats + Lang.serializer

  type Word = String

  type Translation = String

  type WordsRepr = mutable.Map[Word, Translation]

  implicit def words2repr(words: Words): WordsRepr = words.repr
}
