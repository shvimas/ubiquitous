package ubiquitous.backend.translation

import scala.language.implicitConversions

package object abbyy {
  private[abbyy] val host = "https://developers.lingvolive.com"
  type SupportedLang = Lang with AbbyySupport
  type AbbyyLangId = Int
}
