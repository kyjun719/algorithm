package acmicpc._9663

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

var used = booleanArrayOf() // y
var used1 = booleanArrayOf() // x+y
var used2 = booleanArrayOf() // x-y+n-1

fun main() {
    readWriteWithBuffer { br, bw ->
        val n = br.readLine().toInt()

        used = BooleanArray(n)
        used1 = BooleanArray(2*n - 1)
        used2 = BooleanArray(2*n - 1)

        bw.write(solve(n, 0).toString())
    }
}

fun solve(n: Int, y: Int): Int {
    if (y == n) return 1

    var ret = 0
    for (x in 0 until n) {
        if (used[x] || used1[x+y] || used2[x-y+n-1]) {
            continue
        }

        used[x] = true
        used1[x+y] = true
        used2[x-y+n-1] = true

        ret += solve(n, y+1)

        used[x] = false
        used1[x+y] = false
        used2[x-y+n-1] = false
    }

    return ret
}
