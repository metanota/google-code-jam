import scala.io.Source

import java.io.{File, PrintWriter}

object Main extends App {
  val i = Source.fromFile("input.in").getLines()
  val writer = new PrintWriter(new File("output.out"))

  val tries = i.next().toInt
  for (singleTry <- 1 to tries) {
    val firstGuess  = fromRow(i)
    val secondGuess = fromRow(i)
    val result = firstGuess intersect secondGuess
    writer print s"Case #$singleTry: "
    writer print (result.size match {
      case 0 => "Volunteer cheated!"
      case 1 => result.head
      case _ => "Bad magician!"
    })
    writer println()
  }

  writer.close()

  def fromRow(i: Iterator[String]): Set[Int] = {
    val row = i.next().toInt
    var numbers: Set[Int] = null
    for (line <- 1 to 4) {
      if (line == row) numbers = i.next().split(' ').map(_.toInt).toSet
      else i.next()
    }
    numbers
  }

//  def fromRow2(i: Iterator[String]): Set[Int] = {
//    val row = i.next().toInt
//    val seq = 1 to 4 map {_ => i.next()} drop (row - 1) take 1 flatMap { _.split(' ').map(_.toInt) }
//    seq.toSet
//  }
}
