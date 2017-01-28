// test
val p1 = new Poly(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
val p2 = new Poly(0 -> 3.0, 3 -> 7.0)

class Poly(terms0: Map[Int, Double]) {
  val terms = terms0 withDefaultValue 0.0

  // auxiliary constructor
  // * here means it's a repeated parameter, internally all the arguments are represented as a sequence
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  //  def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))

  //  def adjust(term: (Int, Double)) : (Int, Double) = {
  //    val (exp, coeff) = term
  //    exp -> (coeff + terms(exp))
  //  }

  // more efficient way to add polynomials using foldLeft, because it avoid creating the whole list of other.terms map adjust
  def +(other: Poly) = new Poly((other.terms foldLeft terms)(addTerm))

  def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
    val (exp, coeff) = term
    terms + (exp -> (coeff + terms(exp)))
  }

  override def toString =
    (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
}

p1 + p2