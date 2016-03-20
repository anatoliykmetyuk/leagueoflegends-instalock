package lolspam

import subscript.language
import scala.language.implicitConversions

import subscript.Predef._
import subscript.objectalgebra._
import subscript.swing._
import subscript.swing.Scripts._

import scala.swing._
import scala.swing.BorderPanel.Position._

trait LockFrameTrait {this: Frame =>
  def pointer = {
    val pos = java.awt.MouseInfo.getPointerInfo().getLocation()
    Delta(pos.x, pos.y)
  }

  implicit script..
     key(??c: Char) = key2: this, ??c

  script capture(f: Delta => Unit, lbl: Label) = {!pointer!} ~~(d: Delta)~~> [
    f(d)
    let lbl.text = d.serialize
  ]
}