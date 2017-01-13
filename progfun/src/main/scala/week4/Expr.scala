package week4

/**
  * Created by kshang on 13/01/2017.
  */


trait Expr {

  // pattern matching example
  def eval: Int = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
  }

  // pattern match example: return string representation of Expr
  def show: String = this match {
    case Number(n) => n.toString
    case Sum(e1, e2) => e1.show + " + " + e2.show
    // my own question: are these patterns and expressions ok?
    case Prod(Sum(e1, e2), e3) => "(" + Sum(e1, e2).show + ") " + " * " + e3.show
    case Prod(e3, Sum(e1, e2)) => e3.show + " * " + "(" + Sum(e1, e2).show + ") "
    case Prod(e1, e2) => e1.show + " * " + e2.show
    case Var(n) => n
  }
}

case class Number(n: Int) extends Expr

case class Sum(e1: Expr, e2: Expr) extends Expr

case class Prod(e1: Expr, e2: Expr) extends Expr

case class Var(n: String) extends Expr