func bsearch(left, right int) int {
  for left < right {
    mid := left + right >> 1;
    if check(mid) {
      right = mid
      }
    else{
      left = mid + 1
      }
  }
  return left
}
