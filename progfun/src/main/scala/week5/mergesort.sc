
// complexity: O(nlgn)
def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
  val n = xs.length / 2
  if (n == 0) xs
  else {
    // merge two sorted list
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        if (ord.lt(x, y)) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }

    val (fst, snd) = xs splitAt n
    merge(msort(fst), msort(snd))
  }
}

// Scala compiler use the companion object of Ordering for the right type
msort(List(3, 6, 1, 4, 2))
msort(List("apple", "pinapple", "banana"))