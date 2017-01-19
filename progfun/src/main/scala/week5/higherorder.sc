def squareList(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case y :: ys => y * y :: squareList(ys)
}
squareList(List(1, 2, 3))


// shorter and clearer high order function version
def squareListHighOrder(xs: List[Int]): List[Int] =
  xs map (x => x * x)
squareListHighOrder(List(1, 2, 3))


val nums = List(2, -4, 5, 7, 1)
// gives all positive numbers
nums filter (x => x > 0)
// gives all negative numbers
nums filterNot (x => x > 0)
// combination (pair) of filter and filterNot
nums partition (x => x > 0)

// gives the longest prefix of the list such that the predicate is true
nums takeWhile (x => x > 0)
// gives the suffix of the list by dropping takewhile
nums dropWhile (x => x > 0)
// combination (pair) of takeWhile and dropWhile
nums span (x => x > 0)

// pack the adjacent same elements together
def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}
pack(List(1, 1, 1, 2, 2, 2, 5, 6, 6, 1))

// encode the adjacent same element
def encode[T](xs: List[T]): List[(T, Int)] = {
  pack(xs) map (ys => (ys.head, ys.size))
}
encode(List('a', 'a', 'b', 'b', 'b', 'c', 'a'))