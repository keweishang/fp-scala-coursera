import week3.Rational
// one could import multiple names
//import week3.{Rational, Hello}
// one could use wildcard import
//import week3._

val r = new Rational(1, 2)

r.printx

// x is not available as field of class
//r.x
val x = null
val y: String = x

// the type of error is it's a method that
def error(msg: String) = throw new Error(msg)
// error: type mismatch
// val z: Int = null