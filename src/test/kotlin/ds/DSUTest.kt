package ds

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DSUTest {
  @Test
  fun `Should create dsu of zero size`() {
    val dsu = DSU()

    assertEquals(0, dsu.size)
  }

  @Test
  fun `Should create dsu of given size`() {
    val dsu = DSU(5)

    assertEquals(5, dsu.size)

    for (i in 1..5) assertEquals(i, dsu.root(i))
    for (i in 1..5) assertEquals(i, dsu.leader(i))
  }

  @Test
  fun `Should merge two sets`() {
    val dsu = DSU(10)

    dsu.union(1, 2)
    dsu.union(3, 5)
    dsu.merge(6, 4)
    dsu.merge(3, 4)
    dsu.merge(3, 2)
    dsu.merge(7, 8)
    dsu.merge(8, 9)
    dsu.merge(8, 10)

    assertTrue(dsu.isInSameSet(1, 2))
    assertTrue(dsu.isInSameSet(1, 3))
    assertTrue(dsu.isInSameSet(1, 4))
    assertTrue(dsu.isInSameSet(1, 5))
    assertTrue(dsu.isInSameSet(1, 6))
    assertFalse(dsu.isInSameSet(1, 7))
    assertFalse(dsu.isInSameSet(1, 8))
    assertFalse(dsu.isInSameSet(1, 9))
    assertFalse(dsu.isInSameSet(1, 10))

    assertTrue(dsu.isInSameSet(2, 3))
    assertTrue(dsu.isInSameSet(2, 4))
    assertTrue(dsu.isInSameSet(2, 5))
    assertTrue(dsu.isInSameSet(2, 6))
    assertFalse(dsu.isInSameSet(2, 7))
    assertFalse(dsu.isInSameSet(2, 8))
    assertFalse(dsu.isInSameSet(2, 9))
    assertFalse(dsu.isInSameSet(2, 10))

    assertTrue(dsu.isInSameSet(3, 4))
    assertTrue(dsu.isInSameSet(3, 5))
    assertTrue(dsu.isInSameSet(3, 6))
    assertFalse(dsu.isInSameSet(3, 7))
    assertFalse(dsu.isInSameSet(3, 8))
    assertFalse(dsu.isInSameSet(3, 9))
    assertFalse(dsu.isInSameSet(3, 10))

    assertTrue(dsu.isInSameSet(4, 5))
    assertTrue(dsu.isInSameSet(4, 6))
    assertFalse(dsu.isInSameSet(4, 7))
    assertFalse(dsu.isInSameSet(4, 8))
    assertFalse(dsu.isInSameSet(4, 9))
    assertFalse(dsu.isInSameSet(4, 10))

    assertTrue(dsu.isInSameSet(5, 6))
    assertFalse(dsu.isInSameSet(5, 7))
    assertFalse(dsu.isInSameSet(4, 8))
    assertFalse(dsu.isInSameSet(5, 9))
    assertFalse(dsu.isInSameSet(5, 10))

    assertFalse(dsu.isInSameSet(6, 7))
    assertFalse(dsu.isInSameSet(6, 8))
    assertFalse(dsu.isInSameSet(6, 9))
    assertFalse(dsu.isInSameSet(6, 10))

    assertTrue(dsu.isInSameSet(7, 8))
    assertTrue(dsu.isInSameSet(7, 9))
    assertTrue(dsu.isInSameSet(7, 10))
    assertTrue(dsu.isInSameSet(8, 9))
    assertTrue(dsu.isInSameSet(8, 10))
    assertTrue(dsu.isInSameSet(9, 10))
  }

  @Test
  fun `Should fill the element if it is not there`() {
    val dsu = DSU(5)

    assertEquals(10, dsu.leader(10))
    for (i in 1..10) assertEquals(i, dsu.root(i))

    dsu.add(25)

    assertEquals(22, dsu.leader(22))
    assertEquals(25, dsu.leader(25))
    assertEquals(1, dsu.leader(1))

    dsu.union(13, 24)
    dsu.union(25, 24)

    assertEquals(dsu.leader(25), dsu.leader(13))
    assertEquals(dsu.leader(25), dsu.leader(24))
  }
}
