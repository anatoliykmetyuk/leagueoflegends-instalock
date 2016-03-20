package lolspam

import scala.collection.JavaConversions._

import org.apache.commons.io.FileUtils
import java.io.File

object Utils {
  val workdirPath = System.getenv("WORKDIR")

  val workdir = new File(workdirPath)
  def file(name: String) = new File(workdir, name)

  val targetFile = file("target.txt")
  lazy val target = Aim(FileUtils.readLines(targetFile).head)
  def writeTarget(aim: Aim): Unit = FileUtils.write(targetFile, aim.serialize)
}