package acmicpc._10799

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
        val str = br.readLine()
        var i = 0
        var ret = 0
        val stack = ArrayDeque<Char>()
        while (i < str.length) {
            if (str[i] == '(') {
                if (i+1 < str.length && str[i+1] == ')') {
                    ret += stack.size
                    i++
                } else {
                    stack.addLast('(')
                }
            } else {
                stack.removeLast()
                ret++
            }
            i++
        }

        bw.write(ret.toString())
        bw.newLine()
    }
}
