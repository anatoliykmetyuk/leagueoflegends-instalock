package lolspam

import scala.language.implicitConversions
import subscript.language

import subscript.Predef._
import subscript.SubScriptApplication

import Utils._

object Main extends SubScriptApplication {script..
  live =
    if !targetFile.exists then new AimFrame
    new ButtonFrame(target)
}
