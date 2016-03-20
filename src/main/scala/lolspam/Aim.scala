package lolspam

case class Aim(champ: Delta, chat: Delta, lock: Delta) {
  def serialize: String =
    s"${champ.serialize};${chat.serialize};${lock.serialize}"

  def anchor(a: Delta) = copy(
    champ = a + champ
  , chat  = a + chat
  , lock  = a + lock
  )
}

object Aim {
  def apply(str: String): Aim = {
    val ptrn = """(\d+,\d+);(\d+,\d+);(\d+,\d+)""".r
    str match {
      case ptrn(champ, chat, lock) => Aim(Delta(champ), Delta(chat), Delta(lock))
    }
  }
}

case class Delta(x: Int, y: Int) {
  def +(other: Delta) = Delta(x + other.x, y + other.y)
  def -(other: Delta) = Delta(x - other.x, y - other.y)

  def serialize: String = s"$x,$y"
}

object Delta {
  def apply(str: String): Delta = {
    val ptrn = """(\d+),(\d+)""".r
    str match {
      case ptrn(x, y) => Delta(x.toInt, y.toInt)
    }
  }
}

case class Color(raw: Int) {
  def a = (raw & 0xff000000) >> 24
  def r = (raw & 0x00ff0000) >> 16
  def g = (raw & 0x0000ff00) >> 8
  def b = (raw & 0x000000ff)
}