import week4.{Number, Prod, Sum, Var}

// test pattern matching of show method
Sum(Number(1), Number(50)).show

// 2 * X + Y, no parentheses needed
Sum(Prod(Number(2), Var("X")), Var("Y")).show

// (2 + X) * Y, parentheses needed
Prod(Sum(Number(2), Var("X")), Var("Y")).show




// test insertion sort of list
val unsorted = scala.collection.immutable.List(3, 2, 1)
val sorted = isort(unsorted)

// insertion sort of a list
def isort(xs: scala.collection.immutable.List[Int]): scala.collection.immutable.List[Int] = xs match {
  case scala.collection.immutable.List() => scala.collection.immutable.List()
  case y :: ys => insert(y, isort(ys))
}

// insert an element in a sorted list, so that the new list is also sorted
def insert(x: Int, xs: scala.collection.immutable.List[Int]): scala.collection.immutable.List[Int] = xs match {
  case scala.collection.immutable.List() => scala.collection.immutable.List(x)
  case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
}