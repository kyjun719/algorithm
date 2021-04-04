import java.util.*

class RandomizedSet() {
    /** Initialize your data structure here. */
    var map=mutableMapOf<Int,Int>()
    var list=mutableListOf<Int>()
    var random= Random()
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    fun insert(`val`: Int): Boolean {
        if(map[`val`]==null){
            list.add(`val`)
            map[`val`]=list.size-1
            return true
        }
        return false
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    fun remove(`val`: Int): Boolean {
        if(map[`val`]==null){
            return false
        }
        var idx=map[`val`]!!
        var last=list[list.size-1]
        //println("${map} ${list}")
        list[idx]=last
        map[last]=idx
        
        map.remove(`val`)
        list.removeAt(list.size-1)
        //println("${map} ${list}")
        return true
    }

    /** Get a random element from the set. */
    fun getRandom(): Int {
        return list[random.nextInt(list.size)]
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * var obj = RandomizedSet()
 * var param_1 = obj.insert(`val`)
 * var param_2 = obj.remove(`val`)
 * var param_3 = obj.getRandom()
 */