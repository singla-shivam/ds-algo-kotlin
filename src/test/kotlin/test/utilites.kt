package test

import kotlin.test.assertEquals

fun assertIntArray(expected: IntArray, actual: IntArray) {
  assertEquals(expected.size, actual.size)
  for (i in expected.indices) {
    assertEquals(expected[i], actual[i])
  }
}
