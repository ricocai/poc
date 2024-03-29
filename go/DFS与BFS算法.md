序言：
应该尽可能多的看各种各样的题⽬，思考五分钟，想不出来解法的话直接看别⼈的答案。看懂思路就⾏了，甚⾄⾃⼰写⼀遍都没必要，因为⽐较浪费时间。

数据结构的存储⽅式只有两种：数组（顺序存储）和链表（链式存储）。

「图」的两种表示⽅法，邻接表就是链表，邻接矩阵就是⼆维数组。



# DFS
```
func dfs(grid **[]int, x int, y int) {
 isSkip := x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != '1'
 if isSkip {
  return
 }
 grid[x][y] = '2'
 dfs(grid, x-1, y)
 dfs(grid,x,y+1)
 dfs(grid, x+1, y)
 dfs(grid,x, y-1)
}

func numIslands(grid **[]int, gridSize int, gridColSize int) int {
 m := gridSize
 n := gridColSize
 ret := 0
 for i:=0; i < m; i++ {
  for j := 0; j < n; j++ {
   if grid[i][j] == '1' {
     dfs(grid, i, j)
     ret++
   }
  }
 }
 return ret
}
```
递归必须有个终止条件  （边界 + 符合条件的）
遍历过的节点 做个标记，比如 grid[i][j] = '2'   对应是个值  0=48,1=49（ascii）


回溯算法就是个 N 叉树的前后序遍历问题，没有例外。

 

你要是⼼中没有框架，那么你根本⽆法解题，给了你答案，你也不会发现这就是个树的遍历问题

数据结构的基本存储⽅式就是链式和顺序两种，基本操作就是增删查改，遍历⽅式⽆⾮迭代和递归

queue

stack

 

link

list

 

套路

 

 

把树结构理解到位，然后再去看回溯、动规、分治等算法专题，对思路的理解就会更加深刻

把索引左闭右开区间[left, right) 称为⼀个「窗⼝」。

 

二分搜索

分析⼆分查找的⼀个技巧是：不要出现 else，⽽是把所有情况⽤ else if 写清楚，这样可以清楚地展现所有细节

计算 mid 时需要防⽌溢出，代码中 left + (right - left) / 2

 

递归的思想相对迭代思想，稍微有点难以理解，处理的技巧是：不要跳进递归，⽽是利⽤明确的定义来实现算法逻辑。

 

316题 把 1081 题也⼲掉。
题⽬的要求总结出来有三点：
要求⼀、要去重。
要求⼆、去重字符串中的字符顺序不能打乱 s 中字符出现的相对顺序。
要求三、在所有符合上⼀条要求的去重字符串中，字典序最⼩的作为最终结果。

 

所有关于「流」的算法都⽐较难（295），⽐如我们后⽂ ⽔塘抽样算法详解 写过如何从数据流中等概率随机抽取⼀个元素，如果说你没有接触过这个问题的话，还是很难想到解法的。

 

前中后序是遍历⼆叉树过程中处理每⼀个节点的三个特殊时间点，绝不仅仅是三个顺序不同的List：
前序位置的代码在刚刚进⼊⼀个⼆叉树节点的时候执⾏；
后序位置的代码在将要离开⼀个⼆叉树节点的时候执⾏；
中序位置的代码在⼀个⼆叉树节点左⼦树都遍历完，即将开始遍历右⼦树的时候执⾏。

 

【图785】既然说到遍历图，也不涉及最短路径之类的，当然是 DFS 算法和 BFS 皆可了，DFS 算法相对更常⽤些

/* 图遍历框架 */
void traverse(Graph graph, boolean[] visited, int v) {
visited[v] = true;
// 遍历节点 v 的所有相邻节点 neighbor
for (int neighbor : graph.neighbors(v)) {
if (!visited[neighbor]) {
// 相邻节点 neighbor 没有被访问过
// 那么应该给节点 neighbor 涂上和节点 v 不同的颜⾊
traverse(graph, visited, neighbor);
} else {
// 相邻节点 neighbor 已经被访问过
// 那么应该⽐较节点 neighbor 和节点 v 的颜⾊
// 若相同，则此图不是⼆分图
}
}
}


# BFS

BFS 算法起源于⼆叉树的层序遍历，其核⼼是利⽤队列这种数据结构。
且 BFS 算法常⻅于求最值的场景

BFS 的核⼼思想应该不难理解的，就是把⼀些问题抽象成图，从⼀个点开始，向四周开始扩散。⼀般来说，
我们写 BFS 算法都是⽤「队列」这种数据结构，每次将⼀个节点周围的所有节点加⼊队列。



BFS（⼴度优先搜索）的算法框架：

注意，我们的 BFS 算法框架也是 while 循环嵌套 for 循环的形式，也⽤了⼀个 step 变量记录 for 循环执⾏的次数，⽆⾮就是多⽤了⼀个 visited 集合记录⾛过的节点，防⽌⾛回头路罢了。

为什么BFS 找到的路径就是所有可能结果中最短的呢

广度优先搜索找出来的就是最短路径
按照所有的路线走，每次都在所有路线上走一步，第一个到达终点的就是最小路径

https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/

 ```
	func shortestPathBinaryMatrix(grid [][]int) int {
	    if grid[0][0] != 0 {
	        return -1
	    }
	    if len(grid) == 1 && len(grid[0]) == 1 {
	        return 1
	    }
	    paths := [][]int{{1,1}, {1,0}, {0,1}, {0,-1}, {1,-1}, {-1,0}, {-1,1}, {-1,-1}}
	    res := 1
	    queue := make([][]int, 0, 8)
	    queue = append(queue, []int{0, 0})
	    for len(queue) != 0 {
	        res++ // 注意这里++
	        l := len(queue)
	        for i := 0; i < l; i++ { // 注意这里找最短路径需要控制轮数
	            cell := queue[0]
	            queue = append(queue[1:])
	            for _, path := range paths {
	                x, y := cell[0]+path[0], cell[1]+path[1]
	                if x < 0 || y < 0 || x >= len(grid) || y >= len(grid[0]) {
	                    continue
	                }
	                if grid[x][y] == 0 {
	                    grid[x][y] = res  // 给出记录+1，还可以避免重复计算
	                    if x == len(grid)-1 && y == len(grid[0])-1 { // 最先到达终点即为最短路径
	                        return grid[x][y]
	                    }
	                    queue = append(queue, []int{x, y})
	                }
	            }
	        }
	    }
	    return -1
	}
```

树的遍历

后序遍历 https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
```
	/**
	 * Definition for a binary tree node.
	 * type TreeNode struct {
	 *     Val int
	 *     Left *TreeNode
	 *     Right *TreeNode
	 * }
	 */
	func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
	    if root == nil {
	        return root
	    }
	    if root == p || root == q {
	        return root
	    }
	    leftHave := lowestCommonAncestor(root.Left, p, q) 
	    rightHave := lowestCommonAncestor(root.Right, p, q)
	    if leftHave != nil && rightHave != nil {
	        return root
	    }
	    if leftHave == nil {
	        return rightHave
	    }
	    return leftHave
	}
```

中序典型题 https://leetcode-cn.com/problems/validate-binary-search-tree/
```
	/**
	 * Definition for a binary tree node.
	 * type TreeNode struct {
	 *     Val int
	 *     Left *TreeNode
	 *     Right *TreeNode
	 * }
	 */
	func isValidBST(root *TreeNode) bool {
	    pre := math.MinInt64
	    return task(root, &pre)
	}
	 
	func task(root *TreeNode, pre *int) bool {
	    if root == nil {
	        return true
	    }
	    if !task(root.Left, pre) {
	        return false
	    }
	    if root.Val <= *pre {
	        return false
	    }
	    *pre = root.Val
	    return task(root.Right, pre)
	}
```


其实，Dijkstra 可以理解成⼀个带 dp table（或者说备忘录）的 BFS 算法

Dijkstra 算法使⽤优先级队列，主要是为了效率上的优化，类似⼀种贪⼼算法的思路‘’

岛屿系列题⽬的核⼼考点就是⽤ DFS/BFS 算法遍历⼆维数组。



如何⽤ BFS 算法秒杀各种智⼒题

动态规划DP：  是⼒扣第 322 题「零钱兑换」

Union-Find 算法，也就是常说的并查集（Disjoint Set）结构，主要是解决图论中「动态连通性」问题的
 

 

什么算法的难点在「如何穷举」呢？⼀般是递归类问题，最典型的就是动态规划系列问题。
后⽂ 动态规划核⼼套路 阐述了动态规划系列问题的核⼼原理，⽆⾮就是先写出暴⼒穷举解法（状态转移⽅
程），加个备忘录就成⾃顶向下的递归解法了，再改⼀改就成⾃底向上的递推迭代解法了，动态规划的降维
打击 ⾥也讲过如何分析优化动态规划算法的空间复杂度。
上述过程就是在不断优化算法的时间、空间复杂度，也就是所谓「如何聪明地穷举」。

 

⽐如后⽂ Union Find 并查集算法详解 告诉你⼀种⾼效计算连通分量的技巧，理论上说，想判断两个节点是否
连通，我⽤ DFS/BFS 暴⼒搜索（穷举）肯定可以做到，但⼈家 Union Find 算法硬是⽤数组模拟树结构，给
你把连通性相关的操作复杂度给⼲到 O(1) 了。

 

所谓贪⼼算法就是在题⽬中发现⼀些规律（专
业点叫贪⼼选择性质），使得你不⽤完整穷举所有解就可以得出答案。
⼈家动态规划好⽍是⽆冗余地穷举所有解，然后找⼀个最值，你贪⼼算法可好，都不⽤穷举所有解就可以找
到答案，所以前⽂ 贪⼼算法解决跳跃游戏 中贪⼼算法的效率⽐动态规划还⾼。
再⽐如⼤名鼎鼎的 KMP 算法，你写个字符串暴⼒匹配算法很容易，但你发明个 KMP 算法试试？KMP 算法的
本质是聪明地缓存并复⽤⼀些信息，减少了冗余计算，前⽂ KMP 字符匹配算法 就是使⽤状态机的思路实现
的 KMP 算法。
