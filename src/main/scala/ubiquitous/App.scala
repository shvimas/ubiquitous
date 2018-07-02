package ubiquitous

import ubiquitous.backend.Backend
import ubiquitous.frontend.UI


object App extends App {
  val backend = new Backend
  val ui = new UI(backend)
}
