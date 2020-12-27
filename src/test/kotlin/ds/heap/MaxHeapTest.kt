package ds.heap

import org.junit.jupiter.api.Test
import test.assertIntArray
import kotlin.test.*

internal data class Person(val age: Int) : Comparable<Person> {
  override fun compareTo(other: Person): Int {
    if (age < other.age) return -1
    if (age > other.age) return 1
    return 0
  }
}

class MaxHeapTest {
  @Test
  fun `Should create max heap`() {
    val h1 = MaxHeap<Int>()
    assertEquals(0, h1.size)

    val h2 = MaxHeap<String>()
    assertEquals(0, h2.size)

    val h3 = MaxHeap<Boolean>()
    assertEquals(0, h3.size)

    assertNull(h1.peek())
    assertTrue(h1.isEmpty())
  }

  @Test
  fun `Should create max-heap`() {
    val maxHeap = MaxHeap<Int>()

    maxHeap.add(5)
    assertEquals(5, maxHeap.peek())
    assertFalse(maxHeap.isEmpty())
    assertEquals("5", maxHeap.toString())

    maxHeap.add(3)
    assertEquals(5, maxHeap.peek())
    assertEquals("5,3", maxHeap.toString())

    maxHeap.add(10)
    assertEquals(10, maxHeap.peek())
    assertEquals("10,3,5", maxHeap.toString())

    maxHeap.add(4)
    assertEquals(10, maxHeap.peek())
    assertEquals("10,4,5,3", maxHeap.toString())

    maxHeap.add(5)
    assertEquals(10, maxHeap.peek())
    assertEquals("10,5,5,3,4", maxHeap.toString())

    maxHeap.add(11)
    assertEquals(11, maxHeap.peek())
    assertEquals("11,5,10,3,4,5", maxHeap.toString())

    maxHeap.add(12)
    assertEquals(12, maxHeap.peek())
    assertEquals("12,5,11,3,4,5,10", maxHeap.toString())

    maxHeap.add(10)
    assertEquals(12, maxHeap.peek())
    assertEquals("12,10,11,5,4,5,10,3", maxHeap.toString())

    assertEquals(12, maxHeap.poll())
    assertEquals("11,10,10,5,4,5,3", maxHeap.toString())

    assertEquals(11, maxHeap.poll())
    assertEquals("10,10,5,5,4,3", maxHeap.toString())

    assertEquals(10, maxHeap.poll())
    assertEquals("10,5,5,3,4", maxHeap.toString())

    assertEquals(10, maxHeap.poll())
    assertEquals("5,5,4,3", maxHeap.toString())

    assertEquals(5, maxHeap.poll())
    assertEquals("5,3,4", maxHeap.toString())

    assertEquals(5, maxHeap.poll())
    assertEquals("4,3", maxHeap.toString())

    assertEquals(4, maxHeap.poll())
    assertEquals("3", maxHeap.toString())

    assertEquals(3, maxHeap.poll())
    assertEquals("", maxHeap.toString())

    assertEquals(0, maxHeap.size)
    assertNull(maxHeap.peek())
    assertTrue(maxHeap.isEmpty())
  }

  @Test
  fun `Should create heap from array`() {
    val arr = intArrayOf(6, 1, 2, 4, 3, 1, 8, 5, 7)
    val heap = Heap.fromArray(arr.toTypedArray(), MaxHeap())
    assertEquals("8,7,6,5,3,1,2,1,4", heap.toString())
  }

  @Test
  fun `Should add items correctly`() {
    val maxHeap = MaxHeap<Int>()

    maxHeap.add(1)
    assertEquals(1, maxHeap.size)
    assertNotNull(maxHeap.peek())
    assertFalse(maxHeap.isEmpty())
    assertEquals("1", maxHeap.toString())

    maxHeap.add(2)
    assertEquals(2, maxHeap.peek())
    assertEquals("2,1", maxHeap.toString())

    maxHeap.add(3)
    assertEquals(3, maxHeap.peek())
    assertEquals("3,1,2", maxHeap.toString())

    maxHeap.add(4)
    assertEquals(4, maxHeap.peek())
    assertEquals("4,3,2,1", maxHeap.toString())

    maxHeap.add(5)
    assertEquals(5, maxHeap.peek())
    assertEquals("5,4,2,1,3", maxHeap.toString())

    maxHeap.add(6)
    assertEquals(6, maxHeap.peek())
    assertEquals("6,4,5,1,3,2", maxHeap.toString())

    maxHeap.add(7)
    assertEquals(7, maxHeap.peek())
    assertEquals("7,4,6,1,3,2,5", maxHeap.toString())

    maxHeap.add(8)
    assertEquals(8, maxHeap.peek())
    assertEquals("8,7,6,4,3,2,5,1", maxHeap.toString())

    maxHeap.add(9)
    assertEquals(9, maxHeap.peek())
    assertEquals("9,8,6,7,3,2,5,1,4", maxHeap.toString())

    maxHeap.add(10)
    assertEquals(10, maxHeap.peek())
    assertEquals("10,9,6,7,8,2,5,1,4,3", maxHeap.toString())

    maxHeap.add(11)
    assertEquals(11, maxHeap.peek())
    assertEquals("11,10,6,7,9,2,5,1,4,3,8", maxHeap.toString())

    maxHeap.add(12)
    assertEquals(12, maxHeap.peek())
    assertEquals("12,10,11,7,9,6,5,1,4,3,8,2", maxHeap.toString())

    maxHeap.add(13)
    assertEquals(13, maxHeap.peek())
    assertEquals("13,10,12,7,9,11,5,1,4,3,8,2,6", maxHeap.toString())

    maxHeap.add(14)
    assertEquals(14, maxHeap.peek())
    assertEquals("14,10,13,7,9,11,12,1,4,3,8,2,6,5", maxHeap.toString())

    maxHeap.add(15)
    assertEquals(15, maxHeap.peek())
    assertEquals("15,10,14,7,9,11,13,1,4,3,8,2,6,5,12", maxHeap.toString())

    maxHeap.add(16)
    assertEquals(16, maxHeap.peek())
    assertEquals("16,15,14,10,9,11,13,7,4,3,8,2,6,5,12,1", maxHeap.toString())
  }

  @Test
  fun `Should give indices of items in heap tree`() {
    val maxHeap = Heap.fromArray(arrayOf(1, 2, 3, 4, 5, 6, 7), MaxHeap())

    maxHeap.add(13).add(10).add(14).add(3).add(6).add(5).add(10).add(14).add(13).add(12)
      .add(10).add(13).add(3).add(16).add(15).add(7).add(11)

    assertEquals("16,15,14,13,14,11,10,12,13,10,13,6,5,5,6,1,7,4,10,3,3,3,7,2", maxHeap.toString())

    assertIntArray(intArrayOf(19, 20, 21), maxHeap.findAll(3))
    assertIntArray(intArrayOf(15), maxHeap.findAll(1))
    assertIntArray(intArrayOf(17), maxHeap.findAll(4))
    assertIntArray(intArrayOf(23), maxHeap.findAll(2))
    assertIntArray(intArrayOf(12, 13), maxHeap.findAll(5))
    assertIntArray(intArrayOf(11, 14), maxHeap.findAll(6))
    assertIntArray(intArrayOf(16, 22), maxHeap.findAll(7))
    assertIntArray(intArrayOf(6, 9, 18), maxHeap.findAll(10))
    assertIntArray(intArrayOf(5), maxHeap.findAll(11))
    assertIntArray(intArrayOf(7), maxHeap.findAll(12))
    assertIntArray(intArrayOf(3, 8, 10), maxHeap.findAll(13))
    assertIntArray(intArrayOf(2, 4), maxHeap.findAll(14))
    assertIntArray(intArrayOf(1), maxHeap.findAll(15))
    assertIntArray(intArrayOf(0), maxHeap.findAll(16))
  }

  @Test
  fun `Should remove items from heap with heapify down`() {
    val maxHeap = MaxHeap<Int>()

    maxHeap.add(10).add(11).add(3).add(12).add(11)

    assertEquals("12,11,3,10,11", maxHeap.toString())

    maxHeap.remove(12)
    assertEquals("11,11,3,10", maxHeap.toString())

    maxHeap.remove(11)
    assertEquals("10,3", maxHeap.toString())
  }

  @Test
  fun `Should remove items from heap with heapify up`() {
    val maxHeap = MaxHeap<Int>()

    maxHeap.add(3).add(10).add(5).add(6).add(7).add(4).add(6).add(8).add(2).add(1)

    assertEquals("10,8,6,7,6,4,5,3,2,1", maxHeap.toString())

    maxHeap.remove(4)
    assertEquals("10,8,6,7,6,1,5,3,2", maxHeap.toString())
    maxHeap.remove(3)
    assertEquals("10,8,6,7,6,1,5,2", maxHeap.toString())
    maxHeap.remove(5)
    assertEquals("10,8,6,7,6,1,2", maxHeap.toString())
    maxHeap.remove(10)
    assertEquals("8,7,6,2,6,1", maxHeap.toString())
    maxHeap.remove(6)
    assertEquals("8,7,1,2", maxHeap.toString())
    maxHeap.remove(2)
    assertEquals("8,7,1", maxHeap.toString())
    maxHeap.remove(1)
    assertEquals("8,7", maxHeap.toString())
    maxHeap.remove(7)
    assertEquals("8", maxHeap.toString())
    maxHeap.remove(8)
    assertEquals("", maxHeap.toString())
  }

  @Test
  fun `Should work with other classes too`() {
    val heap = MaxHeap<Person>()

    val array = intArrayOf(6, 1, 2, 4, 3, 1, 8, 5, 7)
    array.forEach { e -> heap.add(Person(e)) }

    assertEquals(
      "Person(age=8),Person(age=7),Person(age=6),Person(age=5),Person(age=3),Person(age=1),Person(age=2),Person(age=1),Person(age=4)",
      heap.toString()
    )
  }

  @Test
  fun `Should check contains functions`() {
    val array = intArrayOf(6, 1, 2, 4, 3, 1, 8, 5, 7)
    val heap = Heap.fromArray(array.toTypedArray(), MaxHeap())

    assertTrue(6 in heap)
    assertTrue(heap.contains(3))
    assertFalse(0 in heap)

    assertTrue(heap.containsAll(listOf(1, 2, 3) as Collection<Int>))
    assertFalse(heap.containsAll(listOf(1, 0, 3) as Collection<Int>))
  }
}
