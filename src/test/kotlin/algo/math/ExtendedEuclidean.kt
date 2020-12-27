package algo.math

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ExtendedEuclidean {
  @Test
  fun `Should work fine`() {
    var expected = arrayOf(
      intArrayOf(30),
      intArrayOf(1, -1),
      intArrayOf(6, -5),
    )
    var result = extendedEuclidean(180, 150)
    assertTrue(result contentDeepEquals expected)

    expected = arrayOf(
      intArrayOf(1),
      intArrayOf(-12, 5),
      intArrayOf(-17, 41),
    )
    result = extendedEuclidean(17, 41)
    assertTrue(result contentDeepEquals expected)

    expected = arrayOf(
      intArrayOf(7),
      intArrayOf(-23, 5),
      intArrayOf(-13, 60),
    )
    result = extendedEuclidean(91, 420)
    assertTrue(result contentDeepEquals expected)

    expected = arrayOf(
      intArrayOf(7),
      intArrayOf(5, -23),
      intArrayOf(60, -13),
    )
    result = extendedEuclidean(420, 91)
    assertTrue(result contentDeepEquals expected)
  }
}
