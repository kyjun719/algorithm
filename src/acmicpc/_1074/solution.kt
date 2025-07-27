package acmicpc._1074

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

private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(first + other.first, second + other.second)
}

fun main() {
    readWriteWithBuffer { br, bw ->
        val (n, r, c) = br.readLine().split(" ").map { it.toInt() }

//        bw.write((solve(Pair(0, 0), Pair(r, c), 1 shl n) - 1).toString())

        bw.write((solve2(n, r, c)).toString())
    }
}

fun solve(src: Pair<Int, Int>, dst: Pair<Int, Int>, width: Int): Int {
    if (width == 1) {
        return 1
    }

    val nextWidth = width/2

    var ret = 0

    listOf(Pair(0, 0), Pair(0, nextWidth), Pair(nextWidth, 0), Pair(nextWidth, nextWidth)).forEach {
        (src + it).let { nextSrc ->
            if (
                dst.first >= nextSrc.first && dst.first <= (nextSrc.first + nextWidth - 1) &&
                dst.second >= nextSrc.second && dst.second <= (nextSrc.second + nextWidth - 1)
            ) {
                ret += solve(nextSrc, dst, nextWidth)
                return ret
            } else {
                ret += nextWidth * nextWidth
            }
        }
    }

    return ret
}

fun solve2(n: Int, r: Int, c: Int): Int {
    if (n == 0) return 0

    val half = 1 shl (n-1)

    if (r < half && c < half) {
        return solve2(n-1, r, c)
    }
    // 사분면의 시작점을 (0,0) 으로 이동시키는것과 같은원리?
    if (r < half && c >= half) {
        return half*half + solve2(n-1, r, c-half)
    }
    if (r >= half && c < half) {
        return 2*half*half + solve2(n-1, r-half, c)
    }
    return 3*half*half + solve2(n-1, r-half, c-half)
}
