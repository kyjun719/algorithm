package acmicpc._1515

fun main() {
    val str = readLine()!!.trim()
    println(solve(str, 0, 0))
}

fun solve(str: String, idx: Int, num: Int): Int {
//    println("$str >> $idx, $num")
    if (idx >= str.length) return num - 1

    var matchLength = 0
    val numCharArr = num.toString().toCharArray()

    for (i in numCharArr.indices) {
        val ch = str[idx + matchLength]
        if (numCharArr[i] == ch) {
            matchLength++
            if (idx + matchLength >= str.length) break
        }
    }

    return solve(str, idx + matchLength, num + 1)
}