object factorialTailRecursive {

  def factorial(n: Int): Int = {
    def loop(accumulat: Int, n: Int): Int =
      if (n == 0) accumulat
      else loop(accumulat * n, n - 1)

    loop(1, n)
  }

  factorial(0)
  factorial(1)
  factorial(2)
  factorial(3)
  factorial(4)
  factorial(5)
}
