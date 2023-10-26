	package main
	 
	import (
	    "fmt"   
	    "sort"
	)


	// func cmpString(str1, str2 string) bool {
	//     for i := 0; i < len(str1); i++ {
	//         if str1[i] < str2[i] {
	//             return true
	//         } else if str1[i] > str2[i] {
	//             return false
	//         }
	//     }
	//     return false
	// }
	 
	type strString []string // 注意，这一步很重要，如果不写，就报错
	 
	func (strs strString) Less(i, j int) bool {
	    // return cmpString(strs[i], strs[j])
      if strings.Compare(strs[i], strs[j]) < 0 {
        return true
      }
      return false
	}
	 
	func (strs strString) Len() int {
	    return len(strs)
	}
	 
	func (strs strString) Swap(i, j int) {
	    strs[i], strs[j] = strs[j], strs[i]
	}
	 
	func main (){
		strs := []string{"000FE123", "172BE123", "02FBC239"}
		
		sort.Sort(strString(strs))
		
		for _, str := range strs {
		    fmt.Println(str)
		}
	}
