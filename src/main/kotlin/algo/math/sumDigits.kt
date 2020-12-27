package algo.math

import java.math.BigInteger

val bigZero = BigInteger("0")
val bigTen = BigInteger("10")

tailrec fun sumDigits(num: Long, sum: Int = 0): Int {
  if (num == 0L) return sum
  return sumDigits(num / 10, sum + (num % 10).toInt())
}

fun sumDigits(num: Int): Int {
  return sumDigits(num.toLong())
}

tailrec fun sumDigits(
  num: BigInteger,
  sum: BigInteger = BigInteger("0")
): BigInteger {
  if (num == bigZero) return sum
  return sumDigits(num / bigTen, sum + num % bigTen)
}
