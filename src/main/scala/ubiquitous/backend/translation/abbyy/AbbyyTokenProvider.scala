package ubiquitous.backend.translation
package abbyy

import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.BasicResponseHandler

class AbbyyTokenProvider extends HttpClient {
  private var token = ""

  private val apiKey = getApiKey("ABBYY_LINGVO_API_KEY", this.getClass)

  private def newToken: String = {
    val uri = new URIBuilder(host)
      .setPath("api/v1/authenticate")
      .build()

    execute(
      uri = uri.toString,
      queryParams = List(),
      headers = List(HttpHeaders.AUTHORIZATION -> s"Basic $apiKey"),
      httpRequestClass = classOf[HttpPost],
      responseHandler = new BasicResponseHandler
    )
  }

  def getToken: String = {
    if (token.isEmpty) {
      token = newToken
      token = token.substring(1, token.length - 1) // cut quotes
    }

    token
  }
}
