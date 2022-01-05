package acmicpc._18007

fun main(){
    val arr = IntArray(26) { 1 }
    readLine()!!.forEach { arr[it-'a']++ }
    var ret = 1L
    arr.forEach {
        ret *= it
        ret %= 11092019
    }
    println(ret)
}
