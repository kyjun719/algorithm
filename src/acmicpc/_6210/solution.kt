package acmicpc._6210

var ans = 0L
val power = arrayOf<Long>(1, 10, 100, 1000, 10000, 100000, 1000000, 10000000,
    100000000, 1000000000)

fun main(){
//    calcPrime()

    val (d, n) = readLine()!!.split(" ").map { it.toInt() }

    ans = 2_000_000_000
    for(i in d..9) {
        testWonderPrime(n, d, power[i])
    }
    println(ans)
}

fun prime(n: Long): Boolean {
    if(n in 1..2) return true
    if(n%2 == 0L) return false
    var i = 3
    while(i*i <= n) {
        if(n%i == 0L) return false
        i+=2
    }
    return true
}

fun ndigits(a: Long): Int {
    var tmp = a
    var ans = 0
    while(tmp != 0L) {
        tmp /= 10
        ans++
    }
    return ans
}

fun testWonderPrime(n: Int, d: Int, pow: Long) {
    var n1 = n.toLong()

    if(!prime(n1/pow) || ndigits(n1/pow)<d) {
        n1 -= (n1%pow) + pow/10
    }
    while(!prime(n1%pow) || ndigits(n1%pow)<d) {
        n1++
    }

    if(ndigits(n1/pow)<d) {
        n1 = n1%pow + power[d-1]*pow
    }
    while(!prime(n1/pow)) {
        n1+=pow
    }
    if(n1 < 0) return
    if(n1 < ans) ans = n1
}

fun millerRabin(n: Long, a: Long) : Boolean {
    var r = 0
    var d = n-1
    while(d%2L == 0L) {
        r++
        d = d.shr(1)
    }

    if(r == 0) return false

    var x = pow(a,d,n)

    if(x == 1L) return true

    for(i in 0 until r) {
        if(x == n-1) return true
        if(i == r-1) return false
        x = pow(x,2,n)
    }
    return true
}

fun pow(a: Long, b: Long, d: Long): Long {
    if(b == 0L) return 1
    var ret = 1L
    for(i in 0 until b) {
        ret *= a
        ret %= d
    }
    return ret
}

fun isPrime(n: Long): Boolean {
    if(n <= 1) return false
    if(n%2L == 0L) return false

    for(a in longArrayOf(2,3,5,7,11)) {
        if(a >= n) continue
        if(!millerRabin(n, a)) return false
    }
    return true
}