package acmicpc._9342

fun main() {
    var t = readLine()!!.toInt()
    for(i in 0..t-1) {
        var str = readLine()!!
        println(if(isRight(str))"Infected!" else "Good")
    }
}

val chList=listOf('A','B','C','D','E','F')
fun isRight(str: String) : Boolean {
//    println("${str.lastIndexOf('A')} ${str.lastIndexOf('F')} ${str.lastIndexOf('C')}")
    var isStart = str[0] in chList
    var isEnd = str[str.length-1] in chList
    var fa = str.indexOf('A')
    var la = str.lastIndexOf('A')
    var ff = str.indexOf('F')
    var lf = str.lastIndexOf('F')
    var fc = str.indexOf('C')
    var lc = str.lastIndexOf('C')
//    println("$isStart $fa $la $lf $lc")
    if(isStart && !(fa==0 || fa==1)) {
        return false
    }
    if(la+1 != ff) {
        return false
    }
    if(lf+1 != fc) {
        return false
    }
    return (lc==str.length-1) || isEnd
}