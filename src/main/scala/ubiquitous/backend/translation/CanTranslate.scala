package ubiquitous.backend
package translation

trait CanTranslate {
  def translate(word: Word, from: Lang, to: Lang): Option[TranslationResult]
}