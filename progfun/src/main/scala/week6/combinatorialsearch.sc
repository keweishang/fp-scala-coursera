val n = 7

// generates a Seq of Seqs
(1 until n) map (i =>
  (1 until i) map (j => (i, j)))

// use foldRight
((1 until n) map (i =>
  (1 until i) map (j => (i, j))) foldRight Seq[(Int, Int)]())(_ ++ _)

// use flatten
((1 until n) map (i =>
  (1 until i) map (j => (i, j)))).flatten

// use flatMap
(1 until n) flatMap( i =>
  (1 until i) map (j => (i, j)))

def isPrime(n: Int): Boolean = (2 until n) forall (n % _ != 0)

// get the i, j
((1 until n) flatMap( i =>
  (1 until i) map (j => (i, j)))).filter( pair => isPrime(pair._1 + pair._2))