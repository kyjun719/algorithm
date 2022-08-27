package codejam._2022.qualification.d1000000

fun main() {
    val t = readLine()!!.toInt()
    for(tc in 1..t) {
        val n = readLine()!!.toInt()
        var cnt = 0
        readLine()!!.split(" ")
            .map { it.toInt() }
            .sorted()
            .forEach {
                if(it > cnt) {
                    cnt++
                }
            }
        println("Case #$tc: $cnt")
    }
}