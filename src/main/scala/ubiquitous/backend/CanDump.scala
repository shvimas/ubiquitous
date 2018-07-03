package ubiquitous.backend

import java.nio.file.{Files, Paths}

import org.json4s.native.Serialization.writePretty

trait CanDump[A <: AnyRef] {
  def dumpTo(file: String) {
    val path = Paths.get(file)
    if (!Files.exists(path))
      Files.createDirectories(path.getParent)
    Files.write(path, writePretty[A](whatToDump).getBytes("utf-8"))
  }

  protected def whatToDump: A
}
