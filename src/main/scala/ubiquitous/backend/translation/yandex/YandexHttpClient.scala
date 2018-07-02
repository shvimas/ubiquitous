package ubiquitous.backend
package translation
package yandex

import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.util.EntityUtils
import org.json4s.native.JsonMethods.parse


class YandexHttpClient extends HttpClient with CanGetTranslation[SupportedLang, YandexTranslationResult] {

  private val url = "https://translate.yandex.net/api/v1.5/tr.json/translate"

  private val apiKey = getApiKey("YANDEX_API_KEY", this.getClass)

  private def parseResponse(response: HttpResponse): YandexTranslationResult = {
    val json = EntityUtils.toString(response.getEntity)
    parse(json).extractOrElse[YandexTranslationResult](throw new IllegalArgumentException(s"can't parse $json"))
  }

  override def getTranslation(word: Word, from: SupportedLang, to: SupportedLang): YandexTranslationResult = {
    val queryParams = List("key" -> apiKey, "text" -> word, "lang" -> s"${from.yandexId}-${to.yandexId}")
    val headers = List("Content-type" -> "application/json")

    execute(
      uri = url,
      queryParams = queryParams,
      headers = headers,
      httpRequestClass = classOf[HttpGet],
      responseHandler = parseResponse)
  }
}
