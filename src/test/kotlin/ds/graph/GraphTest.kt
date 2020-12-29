package ds.graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GraphTest {
  @Test
  fun `Should create empty graph`() {
    val graph = Graph<Int>()
    assertEquals("", graph.toString())

    val dfsList = mutableListOf<Int>()
    graph.dfs(1) { node -> dfsList.add(node) }
    assertEquals(0, dfsList.size)
  }

  @Test
  fun `Should add nodes and edges correctly`() {
    val graph = Graph<Int>()
    graph.addNode(1)
    graph.addNode(4)
    graph.addEdge(1, 2)

    assertEquals("1 -> 2\n4 -> \n2 -> 1\n", graph.toString())

    graph.addEdge(5, 6)
    assertEquals(
      """1 -> 2
      |4 -> 
      |2 -> 1
      |5 -> 6
      |6 -> 5
      |""".trimMargin(),
      graph.toString()
    )

    graph.addEdge(3, 4)
    assertEquals(
      """1 -> 2
      |4 -> 3
      |2 -> 1
      |5 -> 6
      |6 -> 5
      |3 -> 4
      |""".trimMargin(),
      graph.toString()
    )

    graph.addEdge(1, 3)
    graph.addNode(8)
    assertEquals(
      """1 -> 2, 3
      |4 -> 3
      |2 -> 1
      |5 -> 6
      |6 -> 5
      |3 -> 4, 1
      |8 -> 
      |""".trimMargin(),
      graph.toString()
    )

    // no duplication of edge
    graph.addEdge(2, 3)
    graph.addEdge(2, 3)
    graph.addEdge(2, 3)
    graph.addNode(8)
    assertEquals(
      """1 -> 2, 3
      |4 -> 3
      |2 -> 1, 3
      |5 -> 6
      |6 -> 5
      |3 -> 4, 1, 2
      |8 -> 
      |""".trimMargin(),
      graph.toString()
    )
  }

  @Test
  fun `Should run dfs-bfs correctly`() {
    val graph = Graph<Int>()
    graph.apply {
      addEdge(1, 2)
      addEdge(1, 3)
      addEdge(1, 4)
      addEdge(2, 4)
      addEdge(3, 5)
      addEdge(3, 10)
      addEdge(4, 6)
      addEdge(4, 7)
      addEdge(4, 8)
      addEdge(5, 6)
      addEdge(5, 9)
      addEdge(6, 9)
      addEdge(7, 4)
    }
    assertEquals(
      """1 -> 2, 3, 4
      |2 -> 1, 4
      |3 -> 1, 5, 10
      |4 -> 1, 2, 6, 7, 8
      |5 -> 3, 6, 9
      |10 -> 3
      |6 -> 4, 5, 9
      |7 -> 4
      |8 -> 4
      |9 -> 5, 6
      |""".trimMargin(),
      graph.toString()
    )

    val dfsList1 = mutableListOf<Int>()
    val dfsList4 = mutableListOf<Int>()
    val dfsList7 = mutableListOf<Int>()
    val bfsList2 = mutableListOf<Int>()
    val bfsList3 = mutableListOf<Int>()
    val bfsList8 = mutableListOf<Int>()
    graph.run {
      dfs(1) { dfsList1.add(it) }
      dfs(4) { dfsList4.add(it) }
      dfs(7) { dfsList7.add(it) }
      bfs(2) { it, _ -> bfsList2.add(it) }
      bfs(3) { it, _ -> bfsList3.add(it) }
      bfs(8) { it, _ -> bfsList8.add(it) }
    }

    assertEquals("1 4 8 7 6 9 5 3 10 2", dfsList1.joinToString(" "))
    assertEquals("4 8 7 6 9 5 3 10 1 2", dfsList4.joinToString(" "))
    assertEquals("7 4 8 6 9 5 3 10 1 2", dfsList7.joinToString(" "))

    assertEquals("2 1 4 3 6 7 8 5 10 9", bfsList2.joinToString(" "))
    assertEquals("3 1 5 10 2 4 6 9 7 8", bfsList3.joinToString(" "))
    assertEquals("8 4 1 2 6 7 3 5 9 10", bfsList8.joinToString(" "))
  }

  @Test
  fun `Should give correct cut vertices`() {
    val graph = Graph<Char>()

    graph.apply {
      addEdge('a', 'b')
      addEdge('a', 'c')
      addEdge('b', 'z')
      addEdge('c', 'd')
      addEdge('c', 'j')
      addEdge('d', 'e')
      addEdge('d', 'f')
      addEdge('e', 'g')
      addEdge('f', 'g')
      addEdge('f', 'h')
      addEdge('f', 'i')
      addEdge('j', 'k')
      addEdge('j', 'l')
      addEdge('j', 'm')
      addEdge('k', 'l')
      addEdge('l', 'm')
    }

    assertEquals(
      """
      |a -> b, c
      |b -> a, z
      |c -> a, d, j
      |z -> b
      |d -> c, e, f
      |j -> c, k, l, m
      |e -> d, g
      |f -> d, g, h, i
      |g -> e, f
      |h -> f
      |i -> f
      |k -> j, l
      |l -> j, k, m
      |m -> j, l
      |""".trimMargin(),
      graph.toString()
    )

    assertEquals("b f d c j a", graph.cutVertices().joinToString(" "))
  }
}
