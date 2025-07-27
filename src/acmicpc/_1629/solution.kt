package acmicpc._1629

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private fun readWriteWithBuffer(func: (BufferedReader, BufferedWriter) -> Unit) {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    bufferedReader.use { br ->
        bufferedWriter.use { bw ->
            func(br, bw)
            bw.flush()
        }
    }
}


fun main() {
    readWriteWithBuffer { br, bw ->
        val (a, b, c) = br.readLine().split(" ").map { it.toInt() }

        bw.write(power(a, b, c).toString())
    }
}

val resultMap = mutableMapOf<Int, Long>()
fun powerWithTwice(a: Int, b: Int, c: Int): Long {
    if (b == 0) return 1
    if (b == 1) return (a % c).toLong()

    val div2 = b/2

    val left = resultMap.getOrPut(div2) {
        powerWithTwice(a, div2, c) % c
    }
    val right = resultMap.getOrPut(b - div2) {
        powerWithTwice(a, b - div2, c) % c
    }

    return left * right % c
}

fun power(a: Int, b: Int, c: Int): Long {
    if (b == 0) return 1
    if (b == 1) return (a % c).toLong()

    val div2 = b/2

    var ret = power(a, div2, c)
    ret = ret * ret % c
    if (b%2 == 0) return ret
    return ret * a % c
}
