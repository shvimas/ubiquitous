package ubiquitous.backend

import ubiquitous.backend.translation.abbyy.{AbbyyHttpClient, AbbyyTranslationResult}
import ubiquitous.backend.translation.yandex.{YandexHttpClient, YandexTranslationResult}

package object translation {
  val YandexTranslator: Translator =
    Translator[yandex.SupportedLang, YandexTranslationResult](
      new YandexHttpClient,
      "yandex",
      "Powered by Yandex.Translate")

  val AbbyyTranslator: Translator =
    Translator[abbyy.SupportedLang, AbbyyTranslationResult](
      new AbbyyHttpClient,
      "abbyy",
      "")

  val allTranslators = List(YandexTranslator, AbbyyTranslator)

  def getApiKey(envVar: String, classObj: Class[_]): String = {
    sys.env.getOrElse(envVar, throw new Exception(s"can't create ${classObj.getName} with empty api key"))
  }
}
