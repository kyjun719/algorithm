package acmicpc._10828

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
//    val stack = ArrayDequeStack()
    val stack = HandmadeStack()

    readWriteWithBuffer { br, bw ->
        repeat(br.readLine().toInt()) {
            val op = StringTokenizer(br.readLine())

            when (op.nextToken()) {
                "push" -> {
                    stack.push(op.nextToken().toInt())
                }
                "pop" -> {
                    stack.pop()
                }
                "size" -> {
                    stack.size()
                }
                "empty" -> {
                    stack.empty()
                }
                "top" -> {
                    stack.top()
                }
                else -> null
            }?.toString()?.let {
                bw.write(it)
                bw.write("\n")
            }
        }
    }
}

private interface CustomStack {
    fun push(n: Int): Int?
    fun pop(): Int
    fun size(): Int
    fun empty(): Int
    fun top(): Int
}

/**
 * ArrayDeque를 사용한 스택 구현체
 */
private class ArrayDequeStack: CustomStack {
    private val stack = ArrayDeque<Int>()
    override fun push(n: Int): Int? {
        stack.addLast(n)
        return null
    }

    override fun pop(): Int {
        return if (stack.isEmpty()) -1 else stack.removeLast()
    }

    override fun size(): Int {
        return stack.size
    }

    override fun empty(): Int {
        return if(stack.isEmpty()) 1 else 0
    }

    override fun top(): Int {
        return if (stack.isEmpty()) -1 else stack.last()
    }
}

/**
 * 직접 만든 스택 구현체
 */
private class HandmadeStack: CustomStack {
    // 0번째 인덱스는 계산편의를 위해 비워두는걸로
    private val arr = IntArray(100_000_005)
    private var tail = 0
    override fun push(n: Int): Int? {
        tail += 1
        arr[tail] = n

        // arr[++tail] = n
        return null
    }

    override fun pop(): Int {
        return (if (tail == 0) -1 else arr[tail]).apply {
            if (tail > 0) tail -= 1
        }

        // return if (tail == 0) -1 else arr[tail--]
    }

    override fun size(): Int {
        return tail
    }

    override fun empty(): Int {
        return if (tail == 0) 1 else 0
    }

    override fun top(): Int {
        return if (tail == 0) -1 else arr[tail]
    }
}