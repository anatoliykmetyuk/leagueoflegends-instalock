package lolspam

import subscript.language
import scala.language.implicitConversions

import subscript.Predef._
import subscript.objectalgebra._
import subscript.swing._
import subscript.swing.Scripts._

import scala.swing._
import scala.swing.BorderPanel.Position._

import Utils._


class AimFrame extends Frame with FrameProcess with LockFrameTrait {

  title = "LoL Instalock Aim"
  location = new Point(400, 300)
  resizable = false

  var anchor: Option[Delta] = None
  var champ : Option[Delta] = None
  var chat  : Option[Delta] = None
  var lock  : Option[Delta] = None

  def delta(d: Option[Delta], pivot: Delta = anchor.get): Delta = d.get - pivot
  def aim = Aim(delta(champ), delta(chat), delta(lock))

  val saveBtn = new Button("Save") {enabled = false}

  val anchorLbl = new Label("Anchor: A"  )
  val champLbl  = new Label("Champion: C")
  val chatLbl   = new Label("Chat: H"    )
  val lockLbl   = new Label("Lock: L"    )

  val mainPanel = new GridPanel(5, 1) {contents ++= Seq(
     anchorLbl
    ,champLbl 
    ,chatLbl  
    ,lockLbl 
    ,saveBtn 
  )}

  contents = mainPanel

  anchorLbl.focusable = true
  anchorLbl.requestFocus
  listenTo(anchorLbl.keys)
  
  script..
    live = controls... / saveBtn save

    controls =;+
      'a' capture(d => anchor = Some(d), anchorLbl)
      'c' capture(d => champ  = Some(d), champLbl )
      'h' capture(d => chat   = Some(d), chatLbl  )
      'l' capture(d => lock   = Some(d), lockLbl  )

    save = writeTarget: aim

}