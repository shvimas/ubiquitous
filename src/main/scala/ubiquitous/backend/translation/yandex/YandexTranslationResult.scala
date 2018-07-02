package ubiquitous.backend.translation
package yandex

case class YandexTranslationResult(code: Int,
                                   lang: String, // not YandexLangId
                                   text: List[String]
                                  ) extends TranslationResult {
  override def get: String = text.mkString(" ")
}
