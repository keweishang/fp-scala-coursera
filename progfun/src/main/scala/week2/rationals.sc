
val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
x.numer
x.denom
//x.sub(y).sub(z)
x - y - z
//y.add(y)
y + y
x < y
x.max(y)
new Rational(2)
-x

class Rational(x: Int, y: Int) {

  require(y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private val g = gcd(x, y)

  def numer = x / g

  def denom = y / g

  //  we could also turn numer and denom into vals
  //  val numer = x / gcd(x, y)
  //  val denom = y / gcd(x, y)

  //  identifier of method could be Alphanumeric
  //  def less(that: Rational) = numer * that.denom < that.numer * denom
  //  def max(that: Rational) = if (this.less(that)) that else this

  //  identifier of method could be operator symbol
  def <(that: Rational) = numer * that.denom < that.numer * denom

  def +(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)


  def max(that: Rational) = if (this < that) that else this

  // prefix operator, use keyword "unary_" as the prefix of the identifier of method
  def unary_- : Rational = new Rational(-numer, denom)

  def -(that: Rational) = this + -that

  override def toString = numer + "/" + denom
}