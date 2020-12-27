package algo.math

import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class Gcd {
  @Test
  fun `Should calculate gcd correctly`() {
    assertEquals(1, gcd(13, 15))
    assertEquals(1, gcd(2, 3))
    assertEquals(2, gcd(2, 4))
    assertEquals(223, gcd(223, 0))
    assertEquals(223, gcd(0, 223))

    assertEquals(8, gcd(2248, 468712))
    assertEquals(8, gcd(468712, 2248))

    assertEquals(16, gcd(125878215888, 674687122578512))
    assertEquals(16, gcd(674687122578512, 125878215888))

    assertEquals(BigInteger("16"), gcd(BigInteger("125878215888"), BigInteger("674687122578512")))
    assertEquals(BigInteger("16"), gcd(BigInteger("674687122578512"), BigInteger("125878215888")))
  }
}
