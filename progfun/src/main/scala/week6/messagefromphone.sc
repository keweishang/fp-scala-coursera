val words = List("Java", "Kata")

val mnem = Map(
  '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
  '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ"
)

// invert the mnem map to give a map from chars 'A' ... 'Z' to '2'...'9'
val charCode: Map[Char, Char] =
  for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit

// maps a word to the digit string, e.g. "Java" -> "5282"
def wordCode(word: String): String =
word.toUpperCase() map charCode // maps are functions


def wordsFromNum: Map[String, Seq[String]] =
  words groupBy wordCode withDefaultValue Seq()

def encode(number: String): Set[List[String]] =
  if (number.isEmpty) Set(List())
  else {
    for {
      split <- 1 to number.length
      word <- wordsFromNum(number take split)
      rest <- encode(number drop split)
    } yield word :: rest
  }.toSet