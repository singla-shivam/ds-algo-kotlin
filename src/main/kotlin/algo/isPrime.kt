package algo

fun isPrime(num: Int): Boolean {
  if (num <= 3) return num > 1

  if (num % 2 == 0 || num % 3 == 0) return false

  var factor = 5
  while (factor * factor <= num) {
    if (
      num % factor == 0 ||
      num % (factor + 2) == 0
    ) return false

    factor += 6
  }

  return true
}
