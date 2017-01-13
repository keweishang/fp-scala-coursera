package week4

import java.util.NoSuchElementException

import week3.{Empty, NonEmpty}

/**
  * Created by kshang on 09/01/2017.
  */
// we use +T as we want List to be covariant
trait List[+T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]


  // fails varience check
  // def prepend(elem: T): List[T] = new Cons(elem, this)
  // use lower bound to solve the problem of covariance
  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)

}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false

  // What about head and tail definition?
  // Actually, we have already implemented using the parameter names.
  // val head: T is actually a legal implementation for the method head: T in the base class. Same to tail.
  // Fields definitions in classes are really special cases of methods, and they can override methods,
  // and they can implement abstract methods and traits.

  // The difference between val and def concerns only the initialization.
  // A val is evaluated when the object is first initialized.
  // A def is evaluated each time it is referenced.
}

//class Nil[T] extends List[T] {
// We only have one instance of Nil, so we change it to object.
// Nothing is the subtype of every class in Scala
object Nil extends List[Nothing] {
  def isEmpty = true

  // The return type Nothing here is optional as the compiler can infer it.
  // Nothing is also the subtype of any other type, so it is a subtype of T too.
  def head: Nothing = throw new NoSuchElementException("Nil.head")

  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

// we can call List(1, 2) to create a list of two elements
// List(1, 2) = list.apply(1, 2)
object List {
  def apply[T](x1: T, x2: T): List[T] = new Cons[T](x1, new Cons[T](x2, Nil))

  def apply[T](x: T): List[T] = new Cons[T](x, Nil)

  def apply[T](): List[T] = Nil
}

object test {
  // test List is covariant
  val x: List[String] = Nil
  val s = "abc"

  // this function returns a result of type List[IntSet]
  def f(xs: List[NonEmpty], x: Empty) = xs prepend x
  val b = s.isInstanceOf
  println(b)
}