package algo.math

import java.math.BigInteger

tailrec fun gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)

tailrec fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)

tailrec fun gcd(x: BigInteger, y: BigInteger): BigInteger {
  return if (y == BigInteger("0")) x else gcd(y, x % y)
}
