package ubiquitous.backend
package translation
package abbyy

import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URIBuilder
import org.apache.http.util.EntityUtils
import org.apache.http.{HttpHeaders, HttpResponse}
import org.json4s.native.JsonMethods.parse

class AbbyyHttpClient extends HttpClient with CanGetTranslation[SupportedLang, AbbyyTranslationResult] {

  private val tokenProvider = new AbbyyTokenProvider

  private def parseResponse(response: HttpResponse): AbbyyTranslationResult = {
    val json = EntityUtils.toString(response.getEntity)
    require(json.nonEmpty, "got empty response")
    parse(json).camelizeKeys.extract[AbbyyTranslationResult]
  }

  override def getTranslation(word: Word, from: SupportedLang, to: SupportedLang): AbbyyTranslationResult = {
    val uri = new URIBuilder(host)
      .setPath("api/v1/Minicard")
      .build()

    val queryParams = List(
      "text" -> word,
      "srcLang" -> from.abbyyId.toString,
      "dstLang" -> to.abbyyId.toString)

    val headers = List(HttpHeaders.AUTHORIZATION -> s"Bearer ${tokenProvider.getToken}")

    execute(
      uri = uri.toString,
      queryParams = queryParams,
      headers = headers,
      httpRequestClass = classOf[HttpGet],
      responseHandler = parseResponse)
  }
}
