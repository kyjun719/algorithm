package acmicpc._10866

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
//    val deque = ArrayDequeDeque()
    val deque = HandmadeDeque()

    readWriteWithBuffer { br, bw ->
        repeat(br.readLine().toInt()) {
            val op = StringTokenizer(br.readLine())

            when (op.nextToken()) {
                "push_front" -> {
                    deque.pushFront(op.nextToken().toInt())
                }
                "push_back" -> {
                    deque.pushBack(op.nextToken().toInt())
                }
                "pop_front" -> {
                    deque.popFront()
                }
                "pop_back" -> {
                    deque.popBack()
                }
                "size" -> {
                    deque.size()
                }
                "empty" -> {
                    deque.empty()
                }
                "front" -> {
                    deque.front()
                }
                "back" -> {
                    deque.back()
                }
                else -> null
            }?.toString()?.let {
                bw.write(it)
                bw.newLine()
            }
        }
    }
}

private interface CustomDeque {
    fun pushFront(n: Int): Int?
    fun pushBack(n: Int): Int?
    fun popFront(): Int
    fun popBack(): Int
    fun size(): Int
    fun empty(): Int
    fun front(): Int
    fun back(): Int
}

/**
 * ArrayDeque를 사용한 덱 구현체
 */
private class ArrayDequeDeque: CustomDeque {
    private val deque = ArrayDeque<Int>()
    override fun pushFront(n: Int): Int? {
        deque.addFirst(n)
        return null
    }

    override fun pushBack(n: Int): Int? {
        deque.addLast(n)
        return null
    }

    override fun popFront(): Int {
        return if (deque.isEmpty()) -1 else deque.removeFirst()
    }

    override fun popBack(): Int {
        return if (deque.isEmpty()) -1 else deque.removeLast()
    }

    override fun size(): Int {
        return deque.size
    }

    override fun empty(): Int {
        return if (deque.isEmpty()) 1 else 0
    }

    override fun front(): Int {
        return if (deque.isEmpty()) -1 else deque.first()
    }

    override fun back(): Int {
        return if (deque.isEmpty()) -1 else deque.last()
    }

}

/**
 * 직업만든 덱 구현체
 */
private class HandmadeDeque: CustomDeque {
    private val arr = IntArray(100_000_005)
    private var head = (100_000_005 / 2)
    private var tail = (100_000_005 / 2)

    override fun pushFront(n: Int): Int? {
        head -= 1
        arr[head] = n
        return null
    }

    override fun pushBack(n: Int): Int? {
        arr[tail] = n
        tail += 1
        return null
    }

    override fun popFront(): Int {
        return if (head == tail) -1 else arr[head].apply {
            if (head != tail) head += 1
        }
    }

    override fun popBack(): Int {
        return if (head == tail) -1 else arr[tail - 1].apply {
            if (head != tail) tail -= 1
        }
    }

    override fun size(): Int {
        return tail - head
    }

    override fun empty(): Int {
        return if (head == tail) 1 else 0
    }

    override fun front(): Int {
        return if (head == tail) -1 else arr[head]
    }

    override fun back(): Int {
        return if (head == tail) -1 else arr[tail - 1]
    }

}
