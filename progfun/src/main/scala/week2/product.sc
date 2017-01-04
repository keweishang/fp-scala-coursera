object product {

  def product(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)
//  {
//    if (a > b) 1 else f(a) * product(f)(a + 1, b)
//  }

  product(x => x * x)(3, 4)

  // a factorial function in terms of product function
  def fact(a: Int): Int = product(x => x)(1, a)

  fact(5)

  // A general function that generalize product and sum.
  // It is a kind of map-reduce, f function is map, combine function is reduce
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  }

  mapReduce(x => x * x, (x, y) => x * y, 1)(2, 3)
  mapReduce(x => x * x, (x, y) => x + y, 0)(2, 3)
}
