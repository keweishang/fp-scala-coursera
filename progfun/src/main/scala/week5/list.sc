// get the last element of list
def last[T](xs: List[T]): T = xs match {
  case List() => throw new Error("list is empty")
  case List(x) => x
  case y :: ys => last(ys)
}

last(List(1, 5, 8))

// get all elements except for the last element of list
def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("list is empty")
  case List(x) => List()
  case y :: ys => y :: init(ys)
}

init(List(1, 5, 8))

// concatenate two lists
// complexity: O(n) where n is the size of list xs
def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case z :: zs => z :: concat(zs, ys)
}

concat(List(10, 20, 30), List(40, 50))

// reverse a list
// complexity: O(n^2) where n is the size of list xs.
// The concatenation ++ takes O(n) time and we do the reverse n times.
def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => xs
  case y :: ys => reverse(ys) ++ List(y)
}

reverse(List(1, 2, 3))

// remove the nth element from the list.
// if n is larger than the size of the list, return the list
def removeAt[T](n: Int, xs: List[T]): List[T] =
//xs match {
//  case List() => xs
//  case y :: ys => if (n == 0) ys else y :: removeAt(n - 1, ys)
//}
(xs take n) ::: (xs drop n + 1)

removeAt(1, List(1, 5, 8))

// flatten all elements in the given list
def flatten(xs: List[Any]): List[Any] = xs match {
  case List() => xs
  case (y: List[_]) :: ys => flatten(y) ++ flatten(ys)
  case y :: ys => y :: flatten(ys)
}

flatten(List(List(1, 1), 2, List(3, List(5, 8))))