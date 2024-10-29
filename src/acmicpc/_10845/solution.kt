package acmicpc._10845

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
//    val queue = ArrayDequeQueue()
    val queue = HandmadeQueue()

    readWriteWithBuffer { br, bw ->
        repeat(br.readLine().toInt()) {
            val op = StringTokenizer(br.readLine())

            when (op.nextToken()) {
                "push" -> {
                    queue.push(op.nextToken().toInt())
                }
                "pop" -> {
                    queue.pop()
                }
                "size" -> {
                    queue.size()
                }
                "empty" -> {
                    queue.empty()
                }
                "front" -> {
                    queue.front()
                }
                "back" -> {
                    queue.back()
                }
                else -> null
            }?.toString()?.let {
                bw.write(it)
                bw.write("\n")
            }
        }
    }
}

private interface CustomQueue {
    fun push(n: Int): Int?
    fun pop(): Int
    fun size(): Int
    fun empty(): Int
    fun front(): Int
    fun back(): Int
}

/**
 * ArrayDeque를 사용하여 구현한 큐 구현체
 */
private class ArrayDequeQueue: CustomQueue {
    private val queue = ArrayDeque<Int>()
    override fun push(n: Int): Int? {
        queue.addLast(n)
        return null
    }

    override fun pop(): Int {
        return if (queue.isEmpty()) -1 else queue.removeFirst()
    }

    override fun size(): Int {
        return queue.size
    }

    override fun empty(): Int {
        return if(queue.isEmpty()) 1 else 0
    }

    override fun front(): Int {
        return if (queue.isEmpty()) -1 else queue.first()
    }

    override fun back(): Int {
        return if (queue.isEmpty()) -1 else queue.last()
    }
}

/**
 * 직접 만든 큐 구현체
 */
private class HandmadeQueue: CustomQueue {
    private val arr = IntArray(100_000_005)
    private var head = 0
    private var tail = 0

    override fun push(n: Int): Int? {
        arr[tail] = n
        tail += 1

        // arr[tail++] = n
        return null
    }

    override fun pop(): Int {
        return (if (head == tail) -1 else arr[head]).apply {
            if (head != tail) head += 1
        }

        // return if (head == tail) -1 else arr[head++]
    }

    override fun size(): Int {
        return tail - head
    }

    override fun empty(): Int {
        return if (tail == head) 1 else 0
    }

    override fun front(): Int {
        return if (tail == head) -1 else arr[head]
    }

    override fun back(): Int {
        return if (tail == head) -1 else arr[tail - 1]
    }
}
