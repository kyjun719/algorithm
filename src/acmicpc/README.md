# rank service
https://solved.ac/problems/level


코틀린은 아래꺼 사용 검토해보기...
```kotlin
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
    ...
    readWriteWithBuffer { br, bw ->
        // contents
    }
    ...
}
```