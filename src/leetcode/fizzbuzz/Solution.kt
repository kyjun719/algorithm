private class fizzbuzz {
    class Solution {
        fun fizzBuzz(n: Int): List<String> {
            var ret = mutableListOf<String>()
            for(i in 1..n) {
                when {
                    (i%15==0) -> ret.add("FizzBuzz")
                    (i%5==0) -> ret.add("Buzz")
                    (i%3==0) -> ret.add("Fizz")
                    else -> ret.add(i.toString())
                }
            }
            return ret
        }
    }
}
