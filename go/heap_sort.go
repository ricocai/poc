package main
import (
    "container/heap"
    "fmt"
)
 
type IntHeap []int
 
func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }     // 大顶堆，返回值决定是否交换元素
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
 
func (h *IntHeap) Push(x interface{}) {
    *h = append(*h, x.(int))
}
 
func (h *IntHeap) Pop() interface{} {
    old := *h
    n := len(old)
    x := old[n-1]
    *h = old[0 : n-1]
    return x
}
 
func TestIntHeap(t *testing.T) {
    pq := &IntHeap{}
    heap.Init(pq)
    for i := 1; i < 10; i++ {
        heap.Push(pq, i)
    }
 
    for len(*pq) > 0 {
        x := heap.Pop(pq)
        fmt.Println(x)
    }
}

