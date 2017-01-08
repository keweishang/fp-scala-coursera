package funsets

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
  * This class is a test suite for the methods in object FunSets. To run
  * the test suite, you can either:
  *  - run the "test" command in the SBT console
  *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
  */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
    * Link to the scaladoc - very clear and detailed tutorial of FunSuite
    *
    * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
    *
    * Operators
    *  - test
    *  - ignore
    *  - pending
    */

  /**
    * Tests are written using the "test" operator and the "assert" method.
    */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
    * For ScalaTest tests, there exists a special equality operator "===" that
    * can be used inside "assert". If the assertion fails, the two values will
    * be printed in the error message. Otherwise, when using "==", the test
    * error message will only say "assertion failed", without showing the values.
    *
    * Try it out! Change the values so that the assertion fails, and look at the
    * error message.
    */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
    * When writing tests, one would often like to re-use certain values for multiple
    * tests. For instance, we would like to create an Int-set and have multiple test
    * about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we can
    * store it in the test class using a val:
    *
    * val s1 = singletonSet(1)
    *
    * However, what happens if the method "singletonSet" has a bug and crashes? Then
    * the test methods are not even executed, because creating an instance of the
    * test class fails!
    *
    * Therefore, we put the shared values into a separate trait (traits are like
    * abstract classes), and create an instance inside each test method.
    *
    */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
    * This test is currently disabled (by using "ignore") because the method
    * "singletonSet" is not yet implemented and the test would fail.
    *
    * Once you finish your implementation of "singletonSet", exchange the
    * function "ignore" by "test".
    */
  test("singletonSet(1) contains 1") {

    /**
      * We create a new instance of the "TestSets" trait, this gives us access
      * to the values "s1" to "s3".
      */
    new TestSets {
      /**
        * The string argument of "assert" is a message that is printed in case
        * the test fails. This helps identifying which assertion failed.
        */
      assert(contains(s1, 1), "Singleton 1")
      assert(!contains(s1, 2), "Singleton 1 does not contain 2")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect is empty if no intersection between 2 sets") {
    new TestSets {
      val u = intersect(s1, s2)
      assert(!contains(u, 1), "intersect does not contain 1")
      assert(!contains(u, 2), "intersect does not contain 2")
    }
  }

  test("intersect contains elements in both sets") {
    new TestSets {
      val u = intersect(union(s1, s2), union(s2, s3))
      assert(!contains(u, 1), "intersect does not contain 1")
      assert(!contains(u, 3), "intersect does not contain 3")
      assert(contains(u, 2), "intersect contains 2")
    }
  }

  test("diff contains elements in 1st set but not in 2nd set") {
    new TestSets {
      val d = diff(union(s1, s2), union(s2, s3))
      assert(contains(d, 1), "diff contains 1")
      assert(!contains(d, 2), "diff does not contain 2")
      assert(!contains(d, 3), "diff does not contain 3")
    }
  }

  test("filter only odd element from set") {
    new TestSets {
      val odd = filter(union(union(s1, s2), s3), (x: Int) => x % 2 == 1)
      assert(contains(odd, 1), "1 meets condition")
      assert(contains(odd, 3), "3 meets condition")
      assert(!contains(odd, 2), "2 does not meet condition")
    }
  }

  test("forall all elements meet condition") {
    new TestSets {
      val all = union(union(s1, s2), s3)
      assert(forall(all, (x: Int) => x < 100), "all elements are smaller than 100")
    }
  }

  test("forall some element does not meet condition") {
    new TestSets {
      val all = union(union(s1, s2), s3)
      assert(!forall(all, (x: Int) => x < 1), "1 is not smaller than 1")
    }
  }

  test("exists at least one element that meet condition") {
    new TestSets {
      val all = union(union(s1, s2), s3)
      assert(exists(all, (x: Int) => x < 2), "1 is smaller than 2")
      assert(exists(all, (x: Int) => x < 100), "all is smaller than 100")
    }
  }

  test("exists no element that meet condition") {
    new TestSets {
      val all = union(union(s1, s2), s3)
      assert(!exists(all, (x: Int) => x < 1), "no element is smaller than 1")
    }
  }

  test("map elements to new values") {
    new TestSets {
      val all = union(union(s1, s2), s3)
      val mapped = map(all, x => x * x)
      assert(contains(mapped, 1), " 1 = 1^2")
      assert(contains(mapped, 4), " 4 = 2^2")
      assert(contains(mapped, 9), " 9 = 3^2")
    }
  }
}
