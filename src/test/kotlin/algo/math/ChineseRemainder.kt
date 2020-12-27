package algo.math

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChineseRemainder {
  @Test
  fun `Should work properly`() {
    var n = intArrayOf(7, 13, 12)
    var a = intArrayOf(4, 4, 0)
    assertEquals(732, chineseRemainder(n, a))

    n = intArrayOf(247, 45, 2471)
    a = intArrayOf(97, 16, 23)
    assertEquals(19713661, chineseRemainder(n, a))

    val n2 = longArrayOf(
      1361,
      31097,
      8978,
    )
    val a2 = longArrayOf(
      168,
      778,
      34,
    )
    assertEquals(124533721320, chineseRemainder(n2, a2))
  }
}
