package acmicpc._2504

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

lateinit var str: String
lateinit var stack: ArrayDeque<Char>
var i: Int = 0
fun main() {
    readWriteWithBuffer { br, bw ->
        str = br.readLine()
        stack = ArrayDeque()
        i = 0

//        var ret = solve()
        var ret = solve2()
        if (stack.isNotEmpty()) ret = 0

        bw.write(ret.toString())
        bw.newLine()
    }
}

private fun solve(): Int {
    var ret = 0

    while (i < str.length) {
        when (val c = str[i]) {
            '(', '[' -> {
                stack.addLast(c)
                i++
                val r = solve()
                if (r == 0) return 0
                ret += r
            }
            ')' -> {
                if (stack.isNotEmpty() && stack.last() == '(') {
                    ret = if (ret == 0) 2 else ret*2
                    i++
                    stack.removeLast()
                    break
                } else {
                    return 0
                }
            }
            ']' -> {
                if (stack.isNotEmpty() && stack.last() == '[') {
                    ret = if (ret == 0) 3 else ret*3
                    i++
                    stack.removeLast()
                    break
                } else {
                    return 0
                }
            }
        }
    }

    return ret
}

private fun solve2(): Int {
    var ret = 0
    var tmp = 1

    for (i in str.indices) {
        when (str[i]) {
            '(' -> {
                stack.addLast('(')
                tmp *= 2
            }
            '[' -> {
                stack.addLast('[')
                tmp *= 3
            }
            ')' -> {
                if (stack.isEmpty() || stack.last() != '(') {
                    return 0
                }
                if (str[i-1] == '(') {
                    ret += tmp
                }
                stack.removeLast()
                tmp /= 2
            }
            ']' -> {
                if (stack.isEmpty() || stack.last() != '[') {
                    return 0
                }
                if (str[i-1] == '[') {
                    ret += tmp
                }
                stack.removeLast()
                tmp /= 3
            }
        }
    }

    return ret
}
