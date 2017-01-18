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