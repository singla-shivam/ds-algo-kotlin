package algo.math

/**
 * ```
 * val n = longArrayOf(3, 5, 7)
 * val a = longArrayOf(2, 3, 2)
 * println(chineseRemainder(n, a)) // 23
 * ```
 * **NOTE** == stands for congurence relation
 * ```
 * x == a1 (mod n1)
 * x == a2 (mod n2)
 * x == a3 (mod n3)
 * ```
 */
fun chineseRemainder(n: LongArray, a: LongArray): Long {
  val prod = n.fold(1L) { acc, e -> acc * e }
  var sum = 0L

  for (i in a.indices) {
    val p = prod / n[i]
    sum += a[i] * mulInv(p, n[i]) * p
  }

  return sum % prod
}

fun chineseRemainder(n: IntArray, a: IntArray): Long {
  return chineseRemainder(
    n.map { it.toLong() }.toLongArray(),
    a.map { it.toLong() }.toLongArray()
  )
}
