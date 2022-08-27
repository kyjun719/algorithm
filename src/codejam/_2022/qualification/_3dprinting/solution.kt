package codejam._2022.qualification._3dprinting

fun main() {
    //find min of each color
    val tc = readLine()!!.toInt()
    val totalInt = 1000000
    for(t in 1..tc) {
        val minArr = IntArray(4) { Int.MAX_VALUE }
        for(i in 0 until 3) {
            readLine()!!.split(" ")
                .map { it.toInt() }
                .forEachIndexed { index, i ->
                    minArr[index] = minOf(minArr[index], i)
                }
        }
        val sum = minArr.sum()
        val sb = StringBuilder("Case #$t:")
        if(sum < totalInt) {
            sb.append(" IMPOSSIBLE")
        } else {
            //calc del
            var minusLeft = sum - totalInt
            minArr.forEach {
                val del = it - minusLeft
                if(del < 0) {
                    sb.append(" 0")
                    minusLeft -= it
                } else {
                    sb.append(" ${it - minusLeft}")
                    minusLeft = 0
                }
            }
        }
        println(sb.toString())
    }
}