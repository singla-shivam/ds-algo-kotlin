package ds

internal data class Vertex(var parent: Int, var rank: Int)

class DSU(_size: Int = 0) {
  private val vertices = MutableList(_size + 1) { i -> Vertex(i, 1) }

  val size
    get() = vertices.size - 1

  private fun fill(end: Int) {
    if (end <= size) return
    for (i in (size + 1)..end) {
      vertices.add(Vertex(i, 1))
    }
  }

  fun leader(v: Int) = root(v)

  fun merge(v1: Int, v2: Int) = union(v1, v2)

  fun add(v: Int) = fill(v)

  fun root(v: Int): Int {
    if (v > size) fill(v)

    val vertex = vertices[v]
    if (vertex.parent != v) vertex.parent = root(vertex.parent)

    return vertex.parent
  }

  fun isInSameSet(v1: Int, v2: Int) = root(v1) == root(v2)

  fun union(v1: Int, v2: Int) {
    var r1 = root(v1)
    var r2 = root(v2)

    if (r1 == r2) return

    var root1 = vertices[r1]
    var root2 = vertices[r2]

    if (root1.rank > root2.rank) {
      // swap
      root1 = root2.also { root2 = root1 }
      r1 = r2.also { r2 = r1 }
    }

    root1.parent = r2
    root2.rank += root1.rank
  }
}
