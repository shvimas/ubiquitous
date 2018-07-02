package ubiquitous.backend

import java.nio.file.{Files, Paths}

import org.json4s.native.Serialization.write

trait CanDump[A <: AnyRef] {
  def dumpTo(file: String) {
    Files.write(Paths.get(file), write[A](whatToDump).getBytes("utf-8"))
  }

  protected def whatToDump: A
}
