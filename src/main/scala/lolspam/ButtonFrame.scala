package lolspam

import subscript.language
import scala.language.implicitConversions

import subscript.Predef._
import subscript.objectalgebra._
import subscript.swing._
import subscript.swing.Scripts._

import scala.swing._
import scala.swing.BorderPanel.Position._



class ButtonFrame(aim: Aim) extends Frame with FrameProcess with LockFrameTrait {

  lazy val bot = new Bot

  var frame: Option[Delta] = None
  def anchoredAim = aim.anchor(frame.get)

  title = "LoL Instalock"
  location = new Point(300, 300)
  resizable = false

  val lockBtn = new Button("Lock") {enabled = false}

  val frameFoundLbl = new Label

  val mainPanel = new GridPanel(2, 1) {contents ++= Seq(
    frameFoundLbl, lockBtn
  )}

  contents = mainPanel
  
  frameFoundLbl.focusable = true
  frameFoundLbl.requestFocus
  listenTo(frameFoundLbl.keys)


  script..
    live =
      if !frame.isDefined then let frameFoundLbl.text = "A - SET FRAME" else let frameFoundLbl.text = "Frame set"
      controls...
      
    controls =;+
      'a' capture(d => frame = Some(d), frameFoundLbl)
      lockBtn lock

    lock =
      // Lock the lane in the chat
      bot.move(anchoredAim.chat)
      bot.click();bot.click()  // Focus on the window, then on the chat
      bot.paste()
      bot.enter()

      // Lock the champion
      bot.move(anchoredAim.champ)
      bot.click()

      // bot.move(anchoredAim.lock)
      // bot.click()
}