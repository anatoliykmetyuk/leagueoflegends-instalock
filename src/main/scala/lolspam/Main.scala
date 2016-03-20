package lolspam

import scala.language.implicitConversions
import subscript.language

import subscript.Predef._
import subscript.SubScriptApplication

import java.awt._
import java.awt.event._

object Main extends SubScriptApplication {
  lazy val bot = new Robot()

  def click() {
    bot.mousePress  (InputEvent.BUTTON1_MASK)
    bot.mouseRelease(InputEvent.BUTTON1_MASK)
    println("Clicked!")
  }

  def paste() {
    bot.keyPress(KeyEvent.VK_META)
    bot.keyPress(KeyEvent.VK_V   )

    bot.keyRelease(KeyEvent.VK_META)
    bot.keyRelease(KeyEvent.VK_V   )
  }

  def enter() {
    bot.keyPress  (KeyEvent.VK_ENTER)
    bot.keyRelease(KeyEvent.VK_ENTER)    
  }

  script..
    live = new AimFrame
}
