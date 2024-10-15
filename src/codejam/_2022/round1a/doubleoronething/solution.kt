package codejam._2022.round1a.doubleoronething

fun main() {
    val t = readLine()!!.toInt()

    for(tc in 1..t) {
        val s = readLine()!!

        val char = s.toCharArray()
        val isFast = BooleanArray(s.length)
        for(i in s.length-2 downTo 0) {
            if(char[i] == char[i+1]) {
                isFast[i] = isFast[i+1]
            } else {
                isFast[i] = (char[i] < char[i+1])
            }
        }

        var ret = ""
        isFast.forEachIndexed { i, b ->
            ret += if(b) "${char[i]}${char[i]}" else char[i]
        }
        println("Case #$tc: $ret")
    }
}

/*
L _ -> false
EL L -> true
EEL EL -> true
PEEL EEL
 */