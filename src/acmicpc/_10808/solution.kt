package acmicpc._10808

//fun main() {
//    IntArray(26).apply {
//        readlnOrNull()?.groupingBy { it }?.eachCount()?.forEach {
//            this[it.key - 'a'] = it.value
//        }
//    }.joinToString(separator = " ").run {
//        println(this)
//    }
//}

fun main() {
    IntArray(26).apply {
        readlnOrNull()?.forEach {
            this[it - 'a']++
        }
    }.joinToString(separator = " ").run {
        println(this)
    }
}