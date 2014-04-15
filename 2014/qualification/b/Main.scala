import scala.annotation.tailrec
import scala.io.Source

import java.io.{File, PrintWriter}

object Main extends App {
  val i = Source.fromFile("input.in").getLines()
  val writer = new PrintWriter(new File("output.out"))

  val tries = i.next().toInt
  for (singleTry <- 1 to tries) {
    val Array(c, f, x) = i.next().split(' ').map(_.toDouble)
    val result =
        BigDecimal(calcTime(c, f, x, 0d, 2d))
          .setScale(7, BigDecimal.RoundingMode.HALF_UP)
          .toDouble
    writer println s"Case #$singleTry: $result"
  }

  writer.close()

  @tailrec
  def calcTime(c: Double, f: Double, x: Double, time: Double, growth: Double): Double = {
    val cleanTimeToWin = x / growth
    val buyTime = c / growth
    val buyTimeToWin = buyTime + x / (growth + f)
    if (cleanTimeToWin < buyTimeToWin) {
      time + cleanTimeToWin
    } else {
      calcTime(c, f, x, time + buyTime, growth + f)
    }
  }
}
