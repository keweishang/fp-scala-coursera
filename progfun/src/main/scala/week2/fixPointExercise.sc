import scala.math.abs

object fixPointExercise {
  val tolerance = 0.0001

  //  Example of functions that can pass function arguments

  def isCloseEnough(x: Double, y: Double) =
    abs((x - y) / x) / x < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      println(guess)
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }

    iterate(firstGuess)
  }

  //  test
  fixedPoint(x => 1 + x / 2)(1)

  //  does not work becuase next value ocsillates between 1 and 2
  //  def sqrt(x: Double) = fixedPoint(y => x / y)(1)

  //  works, but can be better by abstract the average function
  //  def sqrt(x: Double) = fixedPoint(y => (y + x / y) / 2)(1)
  //  sqrt(2)

  //  Example of functions that return functions

  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

  def sqrt(x: Double) = fixedPoint(averageDamp(y => x / y))(1)

  sqrt(2)
}