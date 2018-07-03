package ubiquitous

import ubiquitous.backend.Backend
import ubiquitous.frontend.UI


object App extends App {
  val backend = new Backend
  val ui = new UI(backend)

  val dumpTimer = new java.util.Timer()
  val dumpTask = new java.util.TimerTask {
    def run(): Unit = backend.dump()
  }
  dumpTimer.schedule(dumpTask, 10000L, 10000L)
}
