package acmicpc._1406

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Node(
    var prevIdx: Int = -1,
    var nextIdx: Int = -1,
    var c: Char? = null
)
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    br.readLine().let {
        val arr = Array(600_001) { Node() }
        it.toCharArray().forEachIndexed { index, c ->
            arr[index] = Node(index-1, index + 1, c)
        }
        arr[it.length] = Node(it.length - 1, -1, '\n')

        var addIdx = it.length + 1
        var cursor = it.length

        repeat(br.readLine()?.toInt() ?: 0) {
            val op = StringTokenizer(br.readLine())
            when (op.nextToken()) {
                "L" -> {
                    if (arr[cursor].prevIdx == -1) return@repeat
                    cursor = arr[cursor].prevIdx
                }
                "D" -> {
                    if (arr[cursor].nextIdx == -1) return@repeat
                    cursor = arr[cursor].nextIdx
                }
                "B" -> {
                    if (arr[cursor].prevIdx == -1) return@repeat

                    // 커서왼쪽 문자 삭제
                    arr[arr[cursor].prevIdx].c = '?'

                    // 커서 왼왼쪽과 커서의 왼쪽을 연결
                    val llPtr = arr[arr[cursor].prevIdx].prevIdx
                    arr[cursor].prevIdx = llPtr

                    if (llPtr != -1) {
                        arr[llPtr].nextIdx = cursor
                    }
                }
                "P" -> {
                    val lPtr = arr[cursor].prevIdx

                    // 왼쪽과 새로운 문자 연결
                    arr[addIdx] = Node(lPtr, cursor, op.nextToken().single())
                    if (lPtr != -1) {
                        arr[lPtr].nextIdx = addIdx
                    }
                    arr[cursor].prevIdx = addIdx

                    addIdx++
                }
            }
        }

        var startPtr = cursor
        while (arr[startPtr].prevIdx != -1) {
            startPtr = arr[startPtr].prevIdx
        }

        while (startPtr != -1) {
            bw.append(arr[startPtr].c ?: '?')
            startPtr = arr[startPtr].nextIdx
        }
        bw.flush()
        bw.close()
    }
}


// 시간초과
//fun main() {
//    val br = BufferedReader(InputStreamReader(System.`in`))
//
//    br.readLine().let {
//        val list = it.toMutableList()
//
//        var ptr = list.size
//
//        repeat(br.readLine()?.toInt() ?: 0) {
//            val s = br.readLine()
//            when {
//                s.startsWith('L') -> {
//                    ptr = maxOf(ptr-1, 0)
//                }
//                s.startsWith('D') -> {
//                    ptr = minOf(ptr+1, list.size)
//                }
//                s.startsWith('B') -> {
//                    if (list.isEmpty()) return@repeat
//                    if (ptr - 1 < 0) return@repeat
//                    list.removeAt(ptr-1)
//                    ptr = maxOf(ptr-1, 0)
//                }
//                s.startsWith('P') -> {
//                    list.add(ptr, s.getOrNull(2) ?: '?')
//                    ptr = minOf(ptr+1, list.size)
//                }
//            }
//        }
//
//        println(list.joinToString(separator = ""))
//    }
//}