package algo.math

import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class SumDigits {
  @Test
  fun `Should calculate sum of digits correctly`() {
    assertEquals(0, sumDigits(0))
    assertEquals(1, sumDigits(1))
    assertEquals(1, sumDigits(10))
    assertEquals(2, sumDigits(11))
    assertEquals(64, sumDigits(150457015458757))
    assertEquals(28, sumDigits(150457015))

    assertEquals(BigInteger("64"), sumDigits(BigInteger("150457015458757")))
  }
}
