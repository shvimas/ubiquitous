package ubiquitous.backend.translation

import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.client.utils.URIBuilder
import org.apache.http.client.{ResponseHandler, HttpClient => ApacheHttpClient}
import org.apache.http.impl.client.HttpClients

trait HttpClient {

  protected val requestConfig: RequestConfig =
    RequestConfig
      .custom()
      .setConnectTimeout(5 * 1000)
      .build()

  protected val client: ApacheHttpClient =
    HttpClients
      .custom()
      .setDefaultRequestConfig(requestConfig)
      .build()

  protected def execute[Result, HttpRequestClass <: HttpRequestBase](uri: String,
                                                                     queryParams: List[(String, String)],
                                                                     headers: List[(String, String)],
                                                                     httpRequestClass: Class[HttpRequestClass],
                                                                     responseHandler: ResponseHandler[Result]
                                                                    ): Result = {
    val uriBuilder = new URIBuilder(uri)
    queryParams.foreach(kv => uriBuilder.addParameter(kv._1, kv._2))
    val request = httpRequestClass.newInstance()
    request.setURI(uriBuilder.build())
    headers.foreach(kv => request.setHeader(kv._1, kv._2))
    client.execute(request, responseHandler)
  }
}
