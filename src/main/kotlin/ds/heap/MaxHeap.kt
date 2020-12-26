package ds.heap

class MaxHeap<T : Comparable<T>> : Heap<T>() {
  override fun isInCorrectOrder(first: T, second: T): Boolean {
    return first >= second
  }
}
