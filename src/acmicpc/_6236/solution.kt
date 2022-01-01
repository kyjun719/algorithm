package acmicpc._6236

fun main(){
    val t = readLine()!!.split(" ")
    val n = t[0].toInt()
    val m = t[1].toInt()

    val numArr = IntArray(n)
    for(i in 0 until n) {
        numArr[i] = readLine()!!.toInt()
    }

    println(solve(n,m,numArr))
}

fun solve(n: Int, m: Int, numArr: IntArray): Int {
    var min = 0
    var max = 100000 * 10000 + 1

    for(i in 0 until 35) {
        val mid = (min+max)/2

        if(canCheck(mid, m, 0, 0, numArr)) {
//            println("$mid >> success")
            max = mid+1
        } else {
//            println("$mid >> fail")
            min = mid
        }
    }
//    println("$min $max")
    return (min+max)/2
}

fun canCheck(k: Int, m: Int, left: Long, idx: Int, numArr: IntArray): Boolean {
    var cnt = 0
    var left = 0L
    numArr.forEach {
        if(left < it) {
            left = k.toLong()
            cnt++
        }
        left -= it
        if(left < 0) {
            return false
        }
        if(m < cnt) {
            return false
        }
    }
    return m >= cnt
}