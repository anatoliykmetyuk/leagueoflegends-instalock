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

  var color: Color = Color(0xffffffff)

  title = "LoL Instalock"
  location = new Point(300, 300)
  resizable = false

  val lockBtn   = new Button("Lock"  ) {enabled = false}
  val cancelBtn = new Button("Cancel") {enabled = false}

  val frameFoundLbl = new Label
  val colorSetLbl   = new Label
  val seenColorLbl  = new Label

  val controlPanel = new GridPanel(1, 2) {contents ++= Seq(
    lockBtn, cancelBtn
  )}

  val mainPanel = new GridPanel(4, 1) {contents ++= Seq(
    frameFoundLbl, colorSetLbl, seenColorLbl, controlPanel
  )}

  contents = mainPanel
  
  frameFoundLbl.focusable = true
  frameFoundLbl.requestFocus
  listenTo(frameFoundLbl.keys)


  def colorMatch(c: Color, e: Int = 1): Boolean = (
    math.abs(c.a - color.a) < e &&
    math.abs(c.r - color.r) < e &&
    math.abs(c.g - color.g) < e &&
    math.abs(c.b - color.b) < e
  )


  script..
    live =
      let frameFoundLbl.text = "A - SET FRAME"
      let colorSetLbl  .text = color.raw.toHexString
      controls...
      
    controls =;+
      'a' capture(d => frame = Some(d), frameFoundLbl)
      'c' {:color = bot.color(pointer):} let colorSetLbl.text = color.raw.toHexString
      lockBtn [poolChatColor lock / cancelBtn]

    lock =
      val ptr = pointer
      selectLane
      selectChampion
      times: 3 selectLane sleep: 100  // To make sure
      lockChampion
      bot.move: ptr  // Restore the pointer

    selectLane =
      bot.move(anchoredAim.chat)
      bot.click();bot.click()  // Focus on the window, then on the chat
      bot.paste()
      bot.enter()

    selectChampion =
      bot.move(anchoredAim.champ)
      bot.click()

    lockChampion =
      bot.move(anchoredAim.lock)
      bot.click()
    
    poolChatColor =
      val chatColor = bot.color(anchoredAim.chat)
      let seenColorLbl.text = chatColor.raw.toHexString
      while(!colorMatch(chatColor))
      sleep: 10
}