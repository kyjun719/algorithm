package acmicpc._1920

private class Test {
    fun solveWithBinarySearch() {
        var n = readLine()!!.toInt()
        var arr = readLine()!!.split(" ").map { it.toInt() }
        arr = arr.sorted()
        var m = readLine()!!.toInt()
        readLine()!!.split(" ").map{ findNum(arr, it.toInt()) }
    }

    fun findNum(arr: List<Int>, num: Int) {
        var s=0
        var e=arr.size-1
        while(e>=s) {
            var mid: Int = (s+e)/2
            if(arr[mid]==num) {
                println(1)
                return
            }
            if(arr[mid] > num) {
                e = mid-1
            } else {
                s = mid+1
            }
        }
        println(0)
    }

    fun solveWithMap() {
        var n = readLine()!!.toInt()
        var map = hashMapOf<Int, Boolean>()
        readLine()!!.split(" ").map { map[it.toInt()]=true }
        var m = readLine()!!.toInt()
        var numArr = readLine()!!.split(" ").map{it.toInt()}
        for(i in numArr) {
            if(map[i]!=null) println(1) else println(0)
        }
    }
}
fun main() {
    Test().solveWithBinarySearch()
    //Test().solveWithMap()
}