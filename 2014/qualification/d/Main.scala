import scala.annotation.tailrec
import scala.collection.immutable.SortedSet
import scala.io.Source

import java.io.{File, PrintWriter}

object Main extends App {
  val i = Source.fromFile("input.in").getLines()
  val writer = new PrintWriter(new File("output.out"))

  val tries = i.next().toInt
  for (singleTry <- 1 to tries) {
    val blocksNum = i.next().toInt
    val naomi = i.next().split(' ').map(_.toDouble).toList.sorted
    val ken   = i.next().split(' ').map(_.toDouble)

    val deceitful = playDeceitful(0, naomi, ken.toList.sorted,        blocksNum)
    val fair      = playFair     (0, naomi, SortedSet(ken: _*), blocksNum)
    writer println s"Case #$singleTry: $deceitful $fair"
  }

  writer.close()

  @tailrec
  def playDeceitful(accum: Int, naomi: Seq[Double], ken: Seq[Double], blocksNum: Int): Int = blocksNum match {
    case 0 => accum
    case _ =>
      if (naomi.head < ken.head) playDeceitful(accum,     naomi.tail, ken.init, blocksNum - 1)
      else                       playDeceitful(accum + 1, naomi.tail, ken.tail, blocksNum - 1)
  }

  @tailrec
  def playFair(accum: Int, naomi: Seq[Double], ken: SortedSet[Double], blocksNum: Int): Int = blocksNum match {
      case 0 => accum
      case _ =>
        val naomiStrongest = naomi.last
        val kenStrongest = ken.from(naomiStrongest)
        kenStrongest.isEmpty match {
          case true  => playFair(accum + 1, naomi.init, ken -          ken.firstKey, blocksNum - 1)
          case false => playFair(accum,     naomi.init, ken - kenStrongest.firstKey, blocksNum - 1)
        }
  }
}
