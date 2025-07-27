package utils

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


//fun main() {
//    readWriteWithBuffer { br, bw ->
//
//    }
//}
/**
 * 읽기/쓰기 속도 향상을 위해서 buffered 입출력을 사용
 * @param func BufferedReader, BufferedWriter를 사용하여 실행하는 함수
 */
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


/**
 * 좌표같은 Pair<Int, Int> 형식의 연산을 위한 operator 확장함수
 */
private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(first + other.first, second + other.second)
}

private operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(first - other.first, second - other.second)
}


/**
 * Pair값이 0부터 [maxFirst], [maxSecond] 사이에 있는지 확인
 */
private fun Pair<Int, Int>.isInBound(maxFirst: Int, maxSecond: Int): Boolean {
    return this.first in 0..< maxFirst &&
            this.second in 0 ..< maxSecond
}