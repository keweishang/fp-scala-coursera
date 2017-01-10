import week3._

val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))

def nth[T](n: Int, list: List[T]): T = {
  if (n < 0 || list.isEmpty) throw new IndexOutOfBoundsException()
  else if (n == 0) list.head
  else nth(n - 1, list.tail)
}

nth(2, list)
nth(3, list)