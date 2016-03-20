package lolspam

import subscript.language
import scala.language.implicitConversions

import subscript.Predef._
import subscript.objectalgebra._
import subscript.swing._
import subscript.swing.Scripts._

import scala.swing._
import scala.swing.BorderPanel.Position._



class ButtonFrame(var aim: Option[Aim] = None) extends Frame with FrameProcess {

  var frame: Option[Delta] = None

  title = "LoL Instalock"
  location = new Point(300, 300)
  resizable = false

  val lockBtn = new Button("Lock") {enabled = false}
  val aimBtn  = new Button("Aim" ) {enabled = false}

  val aimedLbl      = new Label
  val frameFoundLbl = new Label


  val controlPanel = new GridPanel(1, 2) {contents ++= Seq(
    lockBtn, aimBtn
  )}

  val mainPanel = new GridPanel(3, 1) {contents ++= Seq(
    aimedLbl, frameFoundLbl, controlPanel
  )}

  contents = mainPanel


  script..
    live =
      if !aim.isDefined   then let aimedLbl.text = "NOT AIMED" else let aimedLbl.text = "Aimed"
      if !frame.isDefined then let frameFoundLbl.text = "FRAME NOT SET" else let frameFoundLbl.text = "Frame set"
      controls
      ...

    controls = {..}

}