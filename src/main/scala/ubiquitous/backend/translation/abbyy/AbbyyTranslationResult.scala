package ubiquitous.backend.translation
package abbyy

case class AbbyyTranslationResult(sourceLanguage: AbbyyLangId,
                                  targetLanguage: AbbyyLangId,
                                  heading: String,
                                  translation: HelperClass
                                 ) extends TranslationResult {
  override def get: String = translation.translation
}

sealed case class HelperClass(translation: String)
