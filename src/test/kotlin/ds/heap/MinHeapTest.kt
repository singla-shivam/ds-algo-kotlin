package ds.heap

import org.junit.jupiter.api.Test
import kotlin.test.*

class MinHeapTest {
  @Test
  // Should create min ds.heap of zero size
  fun createMinHeapOfZeoSize() {
    val h1 = MinHeap<Int>()
    assertEquals(h1.size, 0)

    val h2 = MinHeap<String>()
    assertEquals(h2.size, 0)

    val h3 = MinHeap<Boolean>()
    assertEquals(h3.size, 0)

    assertNull(h1.peek())
    assertTrue(h1.isEmpty())
  }

  @Test
  // Should create min-ds.heap
  fun createMinHeap() {
    val h1 = MinHeap<Int>()

    h1.add(5)
    assertEquals(h1.size, 1)
    assertEquals(h1.peek(), 5)
    assertFalse(h1.isEmpty())
  }
}