package ubiquitous.backend
package translation

import scala.reflect.ClassTag

abstract class Translator(val name: String, val postfix: String) extends CanTranslate

object Translator {
  def apply[L <: Lang : ClassTag, R <: TranslationResult]
  (client: HttpClient with CanGetTranslation[L, R], name: String, postfix: String): Translator = {
    new Translator(name, postfix) {
      override def translate(word: Word, from: Lang, to: Lang): Option[TranslationResult] =
        from match {
          case from: L =>
            to match {
              case to: L =>
                try {
                  Some(client.getTranslation(word, from, to))
                } catch {
                  case _: Throwable =>
//                    TODO: logging?
                    None
                }
              case _ => None
            }
          case _ =>
            None
        }
    }
  }
}
