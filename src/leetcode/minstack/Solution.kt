class MinStack() {

    /** initialize your data structure here. */
    private var list = mutableListOf<Int>()
    private var minList = mutableListOf<Int>()
    fun <T> MutableList<T>.getLastValue(): T {
        return this[this.count()-1]
    }

    fun push(x: Int) {
        list.add(x)
        if(minList.size==0 || minList[minList.size-1]>x) {
            minList.add(x)
        } else {
            minList.add(minList[minList.size-1])
        }
    }

    fun pop() {
        list.removeAt(list.size-1)
        minList.removeAt(minList.size-1)
    }

    fun top(): Int {
        return list.getLastValue()
    }

    fun getMin(): Int {
        return minList.getLastValue()
    }

}

/*
 4번풀이, Pair을 사용함
 */
class MinStack2() {

    /** initialize your data structure here. */
    private var list = mutableListOf<Pair<Int, Int>>()
    fun <T> MutableList<T>.getLastValue(): T {
        return this[this.count()-1]
    }

    fun push(x: Int) {
        if(list.size==0 || list.getLastValue().second>x) {
            list.add(Pair(x,x))
        } else {
            list.add(Pair(x,list.getLastValue().second))
        }
    }

    fun pop() {
        list.removeAt(list.size-1)
    }

    fun top(): Int {
        return list.getLastValue().first
    }

    fun getMin(): Int {
        return list.getLastValue().second
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = MinStack()
 * obj.push(x)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
