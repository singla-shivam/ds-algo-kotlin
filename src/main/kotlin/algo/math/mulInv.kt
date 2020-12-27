package algo.math

/**
 * Returns x where (num * x) % m == 1
 *
 * x exits iff num anc m are co-prime
 */
fun mulInv(num: Long, m: Long): Long {
  if (m == 1L) return 1

  val result = extendedEuclidean(num, m)[1][0]
  return (result % m + m) % m
}

/**
 * Returns x where (num * x) % m == 1
 *
 * x exits iff num anc m are co-prime
 */
fun mulInv(num: Int, m: Int): Int {
  return mulInv(num.toLong(), m.toLong()).toInt()
}
