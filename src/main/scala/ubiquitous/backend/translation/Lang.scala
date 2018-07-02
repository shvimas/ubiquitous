package ubiquitous.backend.translation

import org.json4s.CustomSerializer
import org.json4s.JsonAST.JString
import ubiquitous.backend.translation.abbyy.{AbbyyLangId, AbbyySupport}
import ubiquitous.backend.translation.yandex.{YandexLangId, YandexSupport}

sealed case class Lang(name: String)

object Lang {
  val EN: Lang with YandexSupport with AbbyySupport =
    new Lang("EN")
      with YandexSupport
      with AbbyySupport {
      override val yandexId: YandexLangId = "en"
      override val abbyyId: AbbyyLangId = 1033
    }

  val RU: Lang with YandexSupport with AbbyySupport =
    new Lang("RU")
      with YandexSupport
      with AbbyySupport {
      override val yandexId: YandexLangId = "ru"
      override val abbyyId: AbbyyLangId = 1049
    }

  private val name2lang = List(EN, RU).map(lang => lang.name -> lang).toMap

  val serializer = new CustomSerializer[Lang](_ => ( {
    case JString(name) => name2lang.getOrElse(name, throw new IllegalArgumentException(s"$name is not a valid Lang"))
  }, {
    case lang: Lang => JString(lang.name)
  }
  ))
}
