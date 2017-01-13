import week4.{Number, Prod, Sum, Var}

// test pattern matching of show method
Sum(Number(1), Number(50)).show

// 2 * X + Y, no parentheses needed
Sum(Prod(Number(2), Var("X")), Var("Y")).show

// (2 + X) * Y, parentheses needed
Prod(Sum(Number(2), Var("X")), Var("Y")).show