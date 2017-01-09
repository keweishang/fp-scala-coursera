package week3

import java.util.NoSuchElementException

/**
  * Created by kshang on 09/01/2017.
  */
trait List[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
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

class Nil[T] extends List[T] {
  def isEmpty = true
  // The return type Nothing here is optional as the compiler can infer it.
  // Nothing is also the subtype of any other type, so it is a subtype of T too.
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}