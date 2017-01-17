package patmat

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {

  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)
  }


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a', 'b', 'd'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
  }


  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and quick-encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode empty list of bits") {
    new TestTrees {
      assert(decode(t1, List()) === List())
    }
  }

  test("times of some word") {
    assert(times("abbccd".toList) === List(('d', 1), ('c', 2), ('b', 2), ('a', 1)))
  }

  test("create a small-size code tree and use the code tree for small encoding and decoding") {
    val tree = createCodeTree("ababb".toList)
    assert(decode(tree, encode(tree)("ab".toList)) === "ab".toList)
  }

  test("create a medium-size code tree and use the code tree for small encoding and decoding") {
    val tree = createCodeTree("aabbbdddd".toList)
    assert(decode(tree, encode(tree)("b".toList)) === "b".toList)
  }

  test("create a large-size code tree and use the code tree for small encoding and decoding") {
    val tree = createCodeTree("aabbbbcccddddefghijk".toList)
    assert(decode(tree, encode(tree)("bghhg".toList)) === "bghhg".toList)
  }

}
