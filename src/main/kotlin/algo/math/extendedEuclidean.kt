package algo.math

/**
 * Returns result of Extended euclidean algorithm on <i>a</i> and <i>b</i>.
 *
 * The returned result is 2-D array of size 3.
 * - `GCD = result[0]`
 * - `Bézout coefficients = result[1]`
 * - `Quotients by the gcd = result[2]`
 *
 * The output may have incorrect sign.
 */
fun extendedEuclidean(a: Long, b: Long): Array<LongArray> {
  var oldR = a
  var r = b
  var oldS = 1L
  var s = 0L
  var oldT = 0L
  var t = 1L

  while (r != 0L) {
    val quotient = oldR / r
    oldR = r.also { r = oldR - quotient * it }
    oldS = s.also { s = oldS - quotient * it }
    oldT = t.also { t = oldT - quotient * it }
  }

  return arrayOf(
    longArrayOf(oldR),
    longArrayOf(oldS, oldT),
    longArrayOf(t, s),
  )
}

/**
 * Returns result of Extended euclidean algorithm on <i>a</i> and <i>b</i>.
 *
 * The returned result is 2-D array of size 3.
 * - `GCD = result[0]`
 * - `Bézout coefficients = result[1]`
 * - `Quotients by the gcd = result[2]`
 *
 * The output may have incorrect sign.
 */
fun extendedEuclidean(a: Int, b: Int): Array<IntArray> {
  val result = extendedEuclidean(a.toLong(), b.toLong())
  return Array(3) { i -> result[i].map { it.toInt() }.toIntArray() }
}
