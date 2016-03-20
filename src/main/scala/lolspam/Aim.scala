package lolspam

case class Aim(champ: Delta, chat: Delta, lock: Delta) {
  def serialize: String =
    s"${champ.serialize};${chat.serialize};${lock.serialize}"
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