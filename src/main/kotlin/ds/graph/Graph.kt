package ds.graph

import java.util.Stack
import kotlin.math.min

internal data class NodeWithDepth<T>(val node: T, val depth: Int)

class Graph<T> {
  private val adjacencyMap = mutableMapOf<T, MutableSet<T>>()

  fun addNode(v: T): Boolean {
    if (v in adjacencyMap) return false
    adjacencyMap[v] = mutableSetOf()
    return true
  }

  fun addEdge(v1: T, v2: T): Graph<T> {
    adjacencyMap.getOrPut(v1) { mutableSetOf() }.add(v2)

    adjacencyMap.getOrPut(v2) { mutableSetOf() }.add(v1)

    return this
  }

  fun dfs(startNode: T, block: (node: T) -> Unit) {
    if (startNode !in adjacencyMap) return

    val stack = Stack<T>()
    val visited = mutableMapOf<T, Boolean>().apply {
      adjacencyMap.keys.forEach { put(it, false) }
    }

    stack.push(startNode)

    while (stack.isNotEmpty()) {
      val currentNode = stack.pop()
      if (visited[currentNode]!!) continue

      block(currentNode)
      visited[currentNode] = true

      adjacencyMap[currentNode]?.forEach { stack.push(it) }
    }
  }

  fun bfs(startNode: T, block: (node: T, depth: Int) -> Unit) {
    val queue = ArrayDeque<NodeWithDepth<T>>()
    val visited = mutableMapOf<T, Boolean>().apply {
      adjacencyMap.keys.forEach { put(it, false) }
    }

    queue.addLast(NodeWithDepth(startNode, 0))

    while (queue.isNotEmpty()) {
      val (node, depth) = queue.removeFirst()
      if (visited[node]!!) continue

      block(node, depth)
      visited[node] = true
      adjacencyMap[node]?.forEach { queue.addLast(NodeWithDepth(it, depth + 1)) }
    }
  }

  fun cutVertices(): List<T> {
    val cutVerticesSet = mutableSetOf<T>()
    val entryTime = mutableMapOf<T, Int>()
    val earliestParent = mutableMapOf<T, Int>()
    val visited = mutableMapOf<T, Boolean>()
    var timer = 0

    adjacencyMap.keys.forEach {
      entryTime[it] = -1
      earliestParent[it] = Int.MAX_VALUE
      visited[it] = false
    }

    fun dfs(node: T, parent: T?) {
      visited[node] = true
      entryTime[node] = timer
      earliestParent[node] = timer
      timer++

      var childrenCount = 0
      adjacencyMap[node]?.forEach { child ->
        if (child == parent) return@forEach
        if (visited[child]!!) {
          earliestParent[node] = min(
            earliestParent[node]!!,
            entryTime[child]!!,
          )
        } else {
          dfs(child, node)
          earliestParent[node] = min(earliestParent[node]!!, earliestParent[child]!!)
          if (earliestParent[child]!! >= entryTime[node]!! && parent != null) {
            cutVerticesSet.add(node)
          }
          childrenCount++
        }
      }

      if (parent == null && childrenCount > 1) cutVerticesSet.add(node)
    }

    for (node in visited.keys) {
      if (!visited[node]!!) dfs(node, null)
    }

    return cutVerticesSet.toList()
  }

  override fun toString(): String {
    val buffer = StringBuffer()
    for ((key, value) in adjacencyMap.entries) {
      buffer.run {
        append("$key -> ")
        append(value.joinToString(", ", "", "\n"))
      }
    }

    return buffer.toString()
  }
}
