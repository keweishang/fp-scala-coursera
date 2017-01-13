package week3

/**
  * Created by kshang on 13/01/2017.
  */
abstract class IntSet {

  def incl(x: Int): IntSet

  def contains(x: Int): Boolean

  def union(that: IntSet): IntSet
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def incl(x: Int): IntSet = {
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
  }

  def contains(x: Int): Boolean = {
    if (x < elem) left contains x
    else if (x > elem) right contains x
    true
  }

  override def toString: String = "{" + left + elem + right + "}"

  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem

}

class Empty extends IntSet {

  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)

  def contains(x: Int): Boolean = false

  override def toString: String = "."

  def union(other: IntSet): IntSet = other
}

object test {
  var t1 = new NonEmpty(3, new Empty, new Empty)
  var t2 = t1 incl 4
  var t3 = new NonEmpty(1, new Empty, new Empty) incl 2
  t2.union(t3)
}