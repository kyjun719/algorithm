package codejam._2022.round1a.equalsum

//1
//100
//51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100

fun main() {
    val t = readLine()!!.toInt()
    for(tc in 1..t) {
        val n = readLine()!!.toInt()

        val arr = mutableListOf<Int>()
        for(i in 1..(minOf(23, n))) {
            arr.add(1 shl i+7)
        }
        for(i in 1..n-23+1) {
            arr.add(i)
        }
        println(arr.joinToString(separator = " "))
        System.out.flush()

        arr.addAll(readLine()!!.split(" ").map { it.toInt() })

        arr.sort()

        val sum = IntArray(2)
        val ret = Array<MutableList<Int>>(2) { mutableListOf() }

        arr.forEach {
            if(sum[0] < sum[1]) {
                sum[0] += it
                ret[0].add(it)
            } else {
                sum[1] += it
                ret[1].add(it)
            }
        }

//        println("${ret[0].sum()} , ${ret[1].sum()}")

        println(ret[0].joinToString(separator = " "))

        System.out.flush()
    }
}
