package acmicpc._11729

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
        val k = br.readLine().toInt()

        val ret = hanoi(1, 3, k)

        bw.write(ret.toString())
        bw.newLine()
        moveList.forEach {
            bw.write(it)
            bw.newLine()
        }
    }
}

val moveList = mutableListOf<String>()
fun hanoi(srt: Int, dst: Int, k: Int): Int {
    if (k == 1) {
        moveList.add("$srt $dst")
        return 1
    }

    val other = 6-srt-dst

    return hanoi(srt, other, k-1) + hanoi(srt, dst, 1) + hanoi(other, dst, k-1)
}