import scala.annotation.tailrec
import scala.io.Source

import java.io.{File, PrintWriter}

object Main extends App {
  val i = Source.fromFile("input.in").getLines()
  val writer = new PrintWriter(new File("output.out"))

  val tries = i.next().toInt
  for (singleTry <- 1 to tries) {
    val Array(r, c, m) = i.next().split(' ').map(_.toInt)
    val arr = Array.tabulate(r, c)((_, _) => '.')
    arr(r - 1)(c - 1) = 'c'

    val result = calc(arr, 0, 0, r, c, m)
    writer println s"Case #$singleTry:${System.lineSeparator}$result"
  }

  writer.close()

  @tailrec
  def calc(arr: Array[Array[Char]], xInd: Int, yInd: Int, rows: Int, cols: Int, m: Int): String = {
    if (m == 0) {
      arr.map(new String(_)).mkString(System.lineSeparator)
    } else if (rows - yInd == 2 && cols - xInd == 2) {
      // corner case, start of induction
      if (m == 3) {
        arr(yInd)    (xInd)     = '*'
        arr(yInd + 1)(xInd)     = '*'
        arr(yInd)    (xInd + 1) = '*'
        arr.map(new String(_)).mkString(System.lineSeparator)
      } else {
        "Impossible"
      }
    } else {
      // reduce by induction
      val rowsLeft = rows - yInd
      val colsLeft = cols - xInd
      if (rowsLeft >= colsLeft) {
        val minesDraw = if (m >= colsLeft) cols - 1 else Math.min(xInd + m - 1, cols - 3)
        var minesUsed = 0
        for (x <- xInd to minesDraw) {
          arr(yInd)(x) = '*'
          minesUsed += 1
        }
        calc(arr, xInd, yInd + 1, rows, cols, m - minesUsed)
      } else {
        val minesDraw = if (m >= rowsLeft) rows - 1 else Math.min(yInd + m - 1, rows- 3)
        var minesUsed = 0
        for (y <- yInd to minesDraw) {
          arr(y)(xInd) = '*'
          minesUsed += 1
        }
        calc(arr, xInd + 1, yInd, rows, cols, m - minesUsed)
      }
    }
  }
}
