package ubiquitous.backend

import java.nio.file.{Files, Paths}

import org.json4s.native.Serialization.{read, write}

import scala.collection.mutable

case class Words(repr: WordsRepr) extends CanDump[WordsRepr] {
  override protected def whatToDump: WordsRepr = repr
}

object Words {
  def apply(elems: (Word, Translation)*): Words = new Words(mutable.TreeMap(elems: _*))

  def load(file: String): Words = {
    val str: String = try {
      new String(Files.readAllBytes(Paths.get(file)), charset)
    } catch {
      case _: java.io.IOException => "{}"
    }
    val hashMap = read[mutable.HashMap[Word, Translation]](str)
    apply(hashMap.toList: _*)
  }
}
