package ds.heap

import org.junit.jupiter.api.Test
import test.assertIntArray
import kotlin.test.*

data class Person(val age: Int) : Comparable<Person> {
  override fun compareTo(other: Person): Int {
    if (age < other.age) return -1
    if (age > other.age) return 1
    return 0
  }
}

class MinHeapTest {
  @Test
  fun `Should create min-heap of zero size`() {
    val h1 = MinHeap<Int>()
    assertEquals(0, h1.size)

    val h2 = MinHeap<String>()
    assertEquals(0, h2.size)

    val h3 = MinHeap<Boolean>()
    assertEquals(0, h3.size)

    assertNull(h1.peek())
    assertTrue(h1.isEmpty())
  }

  @Test
  fun `Should create min-heap`() {
    val h1 = MinHeap<Int>()

    h1.add(5)
    assertEquals(1, h1.size)
    assertEquals(5, h1.peek())
    assertFalse(h1.isEmpty())
    assertEquals("5", h1.toString())

    h1.add(3)
    assertEquals(2, h1.size)
    assertEquals(3, h1.peek())
    assertEquals("3,5", h1.toString())

    h1.add(10)
    assertEquals(3, h1.size)
    assertEquals(3, h1.peek())
    assertEquals("3,5,10", h1.toString())

    h1.add(1)
    assertEquals(1, h1.peek())
    assertEquals("1,3,10,5", h1.toString())

    h1.add(1)
    assertEquals(1, h1.peek())
    assertEquals("1,1,10,5,3", h1.toString())

    h1.add(2)
    assertEquals(1, h1.peek())
    assertEquals("1,1,2,5,3,10", h1.toString())

    h1.add(0)
    assertEquals(0, h1.peek())
    assertEquals("0,1,1,5,3,10,2", h1.toString())

    h1.add(1)
    assertEquals(8, h1.size)
    assertEquals(0, h1.peek())
    assertEquals("0,1,1,1,3,10,2,5", h1.toString())

    assertEquals(0, h1.poll())
    assertEquals(7, h1.size)
    assertEquals("1,1,2,1,3,10,5", h1.toString())

    assertEquals(1, h1.poll())
    assertEquals("1,1,2,5,3,10", h1.toString())

    assertEquals(1, h1.poll())
    assertEquals("1,3,2,5,10", h1.toString())

    assertEquals(1, h1.poll())
    assertEquals("2,3,10,5", h1.toString())

    assertEquals(2, h1.poll())
    assertEquals("3,5,10", h1.toString())

    assertEquals(3, h1.poll())
    assertEquals("5,10", h1.toString())

    assertEquals(5, h1.poll())
    assertEquals(1, h1.size)
    assertEquals("10", h1.toString())

    assertEquals(h1.poll(), 10)
    assertEquals(0, h1.size)
    assertEquals("", h1.toString())

    assertEquals(h1.poll(), null)
    assertEquals(0, h1.size)
    assertEquals("", h1.toString())
  }

  @Test
  fun `Should create heap from array`() {
    val arr = intArrayOf(6, 1, 2, 4, 3, 1, 8, 5, 7)
    val heap = Heap.fromArray(arr.toTypedArray(), MinHeap())
    assertEquals("1,3,1,5,4,2,8,6,7", heap.toString())
  }

  @Test
  fun `Should add items correctly`() {
    val minHeap = MinHeap<Int>()

    minHeap.add(16)
    assertEquals(1, minHeap.size)
    assertNotNull(minHeap.peek())
    assertFalse(minHeap.isEmpty())
    assertEquals("16", minHeap.toString())

    minHeap.add(15)
    assertEquals(15, minHeap.peek())
    assertEquals("15,16", minHeap.toString())

    minHeap.add(14)
    assertEquals(14, minHeap.peek())
    assertEquals("14,16,15", minHeap.toString())

    minHeap.add(13)
    assertEquals(13, minHeap.peek())
    assertEquals("13,14,15,16", minHeap.toString())

    minHeap.add(12)
    assertEquals(12, minHeap.peek())
    assertEquals("12,13,15,16,14", minHeap.toString())

    minHeap.add(11)
    assertEquals(11, minHeap.peek())
    assertEquals("11,13,12,16,14,15", minHeap.toString())

    minHeap.add(10)
    assertEquals(10, minHeap.peek())
    assertEquals("10,13,11,16,14,15,12", minHeap.toString())

    minHeap.add(9)
    assertEquals(9, minHeap.peek())
    assertEquals("9,10,11,13,14,15,12,16", minHeap.toString())

    minHeap.add(8)
    assertEquals(8, minHeap.peek())
    assertEquals("8,9,11,10,14,15,12,16,13", minHeap.toString())

    minHeap.add(7)
    assertEquals(7, minHeap.peek())
    assertEquals("7,8,11,10,9,15,12,16,13,14", minHeap.toString())

    minHeap.add(6)
    assertEquals(6, minHeap.peek())
    assertEquals("6,7,11,10,8,15,12,16,13,14,9", minHeap.toString())

    minHeap.add(5)
    assertEquals(5, minHeap.peek())
    assertEquals("5,7,6,10,8,11,12,16,13,14,9,15", minHeap.toString())

    minHeap.add(4)
    assertEquals(4, minHeap.peek())
    assertEquals("4,7,5,10,8,6,12,16,13,14,9,15,11", minHeap.toString())

    minHeap.add(3)
    assertEquals(3, minHeap.peek())
    assertEquals("3,7,4,10,8,6,5,16,13,14,9,15,11,12", minHeap.toString())

    minHeap.add(2)
    assertEquals(2, minHeap.peek())
    assertEquals("2,7,3,10,8,6,4,16,13,14,9,15,11,12,5", minHeap.toString())

    minHeap.add(1)
    assertEquals(1, minHeap.peek())
    assertEquals("1,2,3,7,8,6,4,10,13,14,9,15,11,12,5,16", minHeap.toString())
  }

  @Test
  fun `Should give indices of items in heap tree`() {
    val array = intArrayOf(10, 11, 12, 13, 14, 15, 16)
    val minHeap = Heap.fromArray(array.toTypedArray(), MinHeap())

    minHeap.add(3).add(10).add(4).add(3).add(1).add(5).add(1).add(4).add(11).add(12)
      .add(10).add(3).add(4).add(6).add(5).add(7).add(10)

    assertEquals(
      "1,3,1,3,4,5,3,11,10,4,5,10,12,16,4,13,12,11,10,14,6,10,7,15",
      minHeap.toString()
    )

    assertIntArray(intArrayOf(1, 3, 6), minHeap.findAll(3))
    assertIntArray(intArrayOf(0, 2), minHeap.findAll(1))
    assertIntArray(intArrayOf(4, 9, 14), minHeap.findAll(4))
    assertIntArray(intArrayOf(), minHeap.findAll(0))
    assertIntArray(intArrayOf(5, 10), minHeap.findAll(5))
    assertIntArray(intArrayOf(20), minHeap.findAll(6))
    assertIntArray(intArrayOf(22), minHeap.findAll(7))
    assertIntArray(intArrayOf(8, 11, 18, 21), minHeap.findAll(10))
    assertIntArray(intArrayOf(7, 17), minHeap.findAll(11))
    assertIntArray(intArrayOf(12, 16), minHeap.findAll(12))
    assertIntArray(intArrayOf(15), minHeap.findAll(13))
    assertIntArray(intArrayOf(19), minHeap.findAll(14))
    assertIntArray(intArrayOf(23), minHeap.findAll(15))
    assertIntArray(intArrayOf(13), minHeap.findAll(16))
  }

  @Test
  fun `Should remove items from heap with heapify down`() {
    val minHeap = MinHeap<Int>();

    minHeap.add(3).add(12).add(10).add(11).add(11)

    assertEquals("3,11,10,12,11", minHeap.toString())

    minHeap.remove(3)
    assertEquals("10,11,11,12", minHeap.toString())

    minHeap.remove(3)
    assertEquals(10, minHeap.peek())

    minHeap.remove(11)
    assertEquals("10,12", minHeap.toString())

    minHeap.remove(3)
    assertEquals(10, minHeap.peek())
  }

  @Test
  fun `Should remove items from heap with heapify up`() {
    val minHeap = MinHeap<Int>()

    minHeap.add(3).add(10).add(5).add(6).add(7).add(4).add(6).add(8).add(2).add(1)

    assertEquals("1,2,4,6,3,5,6,10,8,7", minHeap.toString())
    assertEquals("1,2,4,6,3,5,6,10,7", minHeap.remove(8).toString())
    assertEquals("1,2,4,6,3,5,6,10", minHeap.remove(7).toString())
    assertEquals("2,3,4,6,10,5,6", minHeap.remove(1).toString())
    assertEquals("3,6,4,6,10,5", minHeap.remove(2).toString())
    assertEquals("3,5,4,10", minHeap.remove(6).toString())
    assertEquals("3,5,4", minHeap.remove(10).toString())
    assertEquals("3,4", minHeap.remove(5).toString())
    assertEquals("4", minHeap.remove(3).toString())
    assertEquals("", minHeap.remove(4).toString())
  }

  @Test
  fun `Should remove values from heap and correctly re-order the tree`() {
    val minHeap = MinHeap<Int>()

    minHeap.add(1).add(2).add(3).add(4).add(5).add(6).add(7).add(8).add(9)

    assertEquals("1,2,3,4,5,6,7,8,9", minHeap.toString())

    minHeap.remove(2);
    assertEquals("1,4,3,8,5,6,7,9", minHeap.toString())

    minHeap.remove(4);
    assertEquals("1,5,3,8,9,6,7", minHeap.toString())
  }

  @Test
  fun `Should work with other classes too`() {
    val heap = MinHeap<Person>()

    val array = intArrayOf(6, 1, 2, 4, 3, 1, 8, 5, 7)
    array.forEach { e -> heap.add(Person(e)) }

    assertEquals(
      "Person(age=1),Person(age=3),Person(age=1),Person(age=5),Person(age=4),Person(age=2),Person(age=8),Person(age=6),Person(age=7)",
      heap.toString()
    )
  }

  @Test
  fun `Should check contains functions`() {
    val array = intArrayOf(6, 1, 2, 4, 3, 1, 8, 5, 7)
    val heap = Heap.fromArray(array.toTypedArray(), MinHeap())

    assertTrue(6 in heap)
    assertTrue(heap.contains(3))
    assertFalse(0 in heap)

    assertTrue(heap.containsAll(listOf(1, 2, 3) as Collection<Int>))
    assertFalse(heap.containsAll(listOf(1, 0, 3) as Collection<Int>))
  }
}