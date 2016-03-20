package lolspam

import java.awt._
import java.awt.event._

class Bot {
  lazy val bot = new Robot()

  def move(d: Delta) {bot.mouseMove(d.x, d.y)}

  def click() {
    bot.mousePress  (InputEvent.BUTTON1_MASK)
    bot.mouseRelease(InputEvent.BUTTON1_MASK)
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
}