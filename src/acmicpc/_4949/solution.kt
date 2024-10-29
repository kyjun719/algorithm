package acmicpc._4949

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

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
        while (true) {
            val stack = ArrayDeque<Char>()
            val s = br.readLine()
            if (s == ".") break
            var result = true

            s.forEach {
                when (it) {
                    '(', '[' -> stack.addLast(it)
                    ')' -> if (stack.isNotEmpty() && stack.last() == '(') {
                        stack.removeLast()
                    } else {
                        result = false
                        return@forEach
                    }
                    ']' -> if (stack.isNotEmpty() && stack.last() == '[') {
                        stack.removeLast()
                    } else {
                        result = false
                        return@forEach
                    }
                    '.' -> return@forEach
                }
            }
            if (stack.isNotEmpty()) result = false
            if (result) {
                bw.write("yes")
            } else {
                bw.write("no")
            }
            bw.newLine()
        }
    }
}
