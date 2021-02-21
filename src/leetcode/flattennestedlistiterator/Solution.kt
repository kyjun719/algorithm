/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     constructor()
 *
 *     // Constructor initializes a single integer.
 *     constructor(value: Int)
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     fun isInteger(): Boolean
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     fun getInteger(): Int?
 *
 *     // Set this NestedInteger to hold a single integer.
 *     fun setInteger(value: Int): Unit
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     fun add(ni: NestedInteger): Unit
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     fun getList(): List<NestedInteger>?
 * }
 */

class NestedIterator(nestedList: List<NestedInteger>) {
    var idx=0
    var arr=mutableListOf<NestedInteger>()
    init{
        for(i in 0..nestedList.size-1){
            if(!nestedList[i].isInteger()){
                var tmp=mutableListOf<NestedInteger>()
                tmp.addAll(nestedList[i].getList())
                var j=0
                while(j<tmp.size){
                    if(tmp[j].isInteger()){
                        j+=1
                    }else{
                        var a=tmp[j]
                        tmp.removeAt(j)
                        tmp.addAll(j,a.getList())
                    }
                }
                arr.addAll(tmp)
            }else{
                arr.add(nestedList[i])
            }
        }
    }
    fun next(): Int {
        idx+=1
        return arr[idx-1].getInteger()!!
    }
    
    fun hasNext(): Boolean {
        return idx<arr.size
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * var obj = NestedIterator(nestedList)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */