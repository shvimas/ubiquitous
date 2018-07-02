package ubiquitous.backend
package translation

trait CanGetTranslation[L <: Lang, R <: TranslationResult] {
  def getTranslation(word: Word, from: L, to: L): R
}
