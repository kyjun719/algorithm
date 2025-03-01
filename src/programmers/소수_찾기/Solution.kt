package programmers.소수_찾기

class Solution {
    val numberSet = mutableSetOf<Int>()

    fun solution(numbers: String): Int {
        permutation("", numbers)
        return numberSet.count { isPrime(it) }
    }

    fun permutation(prefix: String, str: String) {
        val n = str.length
        if (prefix.isNotEmpty()) {
            numberSet.add(prefix.toInt())
        }
        for (i in 0 until n) {
            permutation(prefix + str[i], str.substring(0, i) + str.substring(i + 1, n))
        }
    }

    fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }
}
