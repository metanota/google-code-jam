import scala.annotation.tailrec
import scala.io.Source

import java.io.{File, PrintWriter}

object Main extends App {
  val i = Source.fromFile("input.in").getLines()
  val writer = new PrintWriter(new File("output.out"))

  val tries = i.next().toInt
  for (singleTry <- 1 to tries) {
    val n = i.next().toInt
    val input = 1 to n map {_ => i.next().toString}
    val result = solve(input)
    writer println s"Case #$singleTry: $result"
  }

  writer.close()

  @tailrec
  def solve(l: Seq[String], accum: Int = 0): String = {
    if (l.forall(_.isEmpty)) {
      accum.toString
    } else if (l.exists(_.isEmpty)) {
      "Fegla Won"
    } else {
      val c = l.head.charAt(0)
      val grouped = l map {_ span {_ == c}}
      val letters = grouped.map{_._1.size}.sorted
      val median = letters.drop(letters.size / 2).head
      val newAccum = (accum /: letters) {(acc, value) => acc + Math.abs(median - value)}
      solve(grouped.map{_._2}, newAccum)
    }
  }
}
