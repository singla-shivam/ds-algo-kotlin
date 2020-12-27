package algo.math

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MulInv {
  @Test
  fun `Should calculate inverse correctly`() {
    assertEquals(1, mulInv(1, 17))
    assertEquals(2, mulInv(9, 17))
    assertEquals(9, mulInv(2, 17))
    assertEquals(4, mulInv(13, 17))
    assertEquals(13, mulInv(4, 17))
    assertEquals(13, mulInv(4, 17))

    assertEquals(9, mulInv(3, 26))
    assertEquals(21, mulInv(5, 26))
    assertEquals(51818664, mulInv(1234, 150457015))
    assertEquals(146433448594787, mulInv(1234, 150457015458757))
  }
}
