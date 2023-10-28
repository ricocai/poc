# References
算法可视化
http://www.u396.com/wp-content/collection/data-structure-visualizations/

http://www.u396.com/wp-content/collection/data-structure-visualizations/DFS.html

# go版数据结构及框架
【结构基础  vs  上层建筑】

线性就是for迭代为代表，非线性就是递归为代表  【迭代 vs 递归】

典型的线性迭代结构
```
func traverse(arr []int){
    for i,v := range arr{
        // i为下标，v为值。可以迭代访问arr[i]或者直接输出v
    }
}
```

链表遍历框架，兼具迭代和递归结构：
```
/* 基本的单链表节点 go没有class,只有struct*/
type ListNode struct{
    val int
    next *ListNode 
}

func traverse(head *ListNode){
    for ListNode p = head; p != nil; p = p.next{
        // 迭代遍历p.val
    }
}

func traverse(head *ListNode){
    // 前序遍历fmt.println(head.val)，正序打印链表
    traverse(head.next)
    // 后序遍历，倒序打印链表
}
```

二叉树遍历框架，典型的非线性**递归**遍历结构：
```
/*基本的二叉树节点*/
type TreeNode struct{
    Val int
    Left,Right *TreeNode
}

func traverse(root *TreeNode){
    // 前序遍历
    traverse(root.Left)
    // 中序遍历
    traverse(root.Right)
    // 后序遍历
}
```

N 叉树的遍历框架：
```
/* 基本的N叉树节点 */
type TreeNode struct{
    val int
    children []TreeNode
}

func traverse(root *TreeNode){
    for TreeNode child := range root.children{
        traverse(child)
    }
}
```
N 叉树的遍历又可以扩展为图的遍历，因为图就是好几 N 叉棵树的结合体。你说图是可能出现环的？这个很好办，用个布尔数组 visited 做标记就行了


数据结构的存储方式只有两种：数组（顺序存储）和链表（链式存储）【数组 vs 链表】

基于数组的   Queue、Stack实现（处理扩容）
基于链表的   Queue、Stack实现（天然具备扩容）

map  本质是 hash表， 相当于大数组

「图」的两种表示方法，邻接表就是链表，邻接矩阵就是二维数组

基于Array实现的 树 === 堆Heap 就是 一个**完全二叉树**
基于链表实现的  树 === 链表结构的 树 ===》 不一定是完全二叉树； 衍生出各种巧妙的设计，比如二叉搜索树、AVL 树、红黑树、区间树、B 树等等，以应对不同的问题。







