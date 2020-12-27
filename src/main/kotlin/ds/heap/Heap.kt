package ds.heap

abstract class Heap<T : Comparable<T>> : Collection<T> {
  private val elements = arrayListOf<T>()
  override val size
    get() = elements.size

  private fun removeLast() = elements.removeAt(size - 1)

  private fun leftChildIndex(index: Int) = index shl 1 or 1 // (2 * index + 1)

  private fun rightChildIndex(index: Int) = (index + 1) shl 1 // (2 * index + 2)

  private fun parentIndex(index: Int) = (index - 1) shr 1 // ((index - 1) / 2)

  private fun leftChild(parentIndex: Int) = elements.elementAtOrNull(leftChildIndex(parentIndex))

  private fun rightChild(parentIndex: Int) = elements.elementAtOrNull(rightChildIndex(parentIndex))

  private fun parent(childIndex: Int) = elements.elementAtOrNull(parentIndex(childIndex))

  private fun hasLeftChild(parentIndex: Int) = leftChild(parentIndex) != null

  private fun hasRightChild(parentIndex: Int) = rightChild(parentIndex) != null

  private fun hasParent(childIndex: Int) = parent(childIndex) != null

  private fun swap(index1: Int, index2: Int) {
    elements[index1] = elements[index2].also {
      elements[index2] = elements[index1]
    }
  }

  private fun heapifyDown(index: Int = 0) {
    var currentIndex = index

    while (hasLeftChild(currentIndex)) {
      // check whether it has right child,
      // and it is candidate to be exchanged with parent
      val nextIndex: Int = if (
        hasRightChild(currentIndex) &&
        isInCorrectOrder(rightChild(currentIndex)!!, leftChild(currentIndex)!!)
      ) rightChildIndex(currentIndex)
      else leftChildIndex(currentIndex)

      if (isInCorrectOrder(elements[currentIndex], elements[nextIndex])) break

      swap(currentIndex, nextIndex)
      currentIndex = nextIndex
    }
  }

  private fun heapifyUp(index: Int = size - 1) {
    if (index >= size) return

    var currentIndex = index

    while (
      hasParent(currentIndex) &&
      !isInCorrectOrder(parent(currentIndex)!!, elements[currentIndex])
    ) {
      val parentIndex = parentIndex(currentIndex)
      swap(parentIndex, currentIndex)
      currentIndex = parentIndex
    }
  }

  fun findAll(item: T): IntArray {
    val indices = ArrayList<Int>()
    for ((i, element) in elements.withIndex()) {
      if (element == item) indices.add(i)
    }

    return indices.toIntArray()
  }

  fun find(item: T): Int {
    for ((i, element) in elements.withIndex()) {
      if (element == item) return i
    }
    return -1
  }

  fun peek() = elements.elementAtOrNull(0)

  fun poll(): T? {
    if (isEmpty()) return null

    val item = elements.first()
    val lastElement = removeLast()
    if (size > 0) elements[0] = lastElement
    heapifyDown()

    return item
  }

  fun add(element: T): Heap<T> {
    elements.add(element)
    heapifyUp()
    return this
  }

  fun remove(element: T): Heap<T> {
    var index = find(element)

    while (index != -1) {
      val lastElement = removeLast()
      if (index < size) elements[index] = lastElement
      heapifyUp(index)
      heapifyDown(index)
      index = find(element)
    }

    return this
  }

  override fun contains(element: T): Boolean {
    return find(element) != -1
  }

  override fun isEmpty(): Boolean = elements.size == 0

  override fun containsAll(elements: Collection<T>): Boolean {
    return elements.all { contains(it) }
  }

  override fun iterator(): Iterator<T> {
    TODO("Not implemented")
  }

  override fun toString(): String {
    return elements.joinToString(",")
  }

  abstract fun isInCorrectOrder(first: T, second: T): Boolean

  companion object {
    fun <H : Heap<T>, T : Comparable<T>> fromArray(array: Array<T>, heap: H): Heap<T> {
      array.forEach { e -> heap.add(e) }
      return heap
    }
  }
}
