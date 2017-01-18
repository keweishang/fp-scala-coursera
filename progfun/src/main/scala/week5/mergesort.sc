// merge two sorted list
def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
  case (Nil, _) => ys
  case (_, Nil) => xs
  case (x :: xs1, y :: ys1) =>
    if (x < y) x :: merge(xs1, ys)
    else y :: merge(xs, ys1)
}

def msort(xs: List[Int]): List[Int] = {
  val n = xs.length / 2
  if (n == 0) xs
  else {
    val (fst, snd) = xs splitAt n
    merge(msort(fst), msort(snd))
  }
}

msort(List(3, 6, 1, 4, 2))