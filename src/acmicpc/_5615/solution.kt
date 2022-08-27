package acmicpc._5615

fun main(){
    var k = readLine()!!.toInt()
    var cnt = 0
    for(i in 0 until k) {
        var n = readLine()!!.toLong()
        n = 2*n+1

        if(isPrime(n)) {
            cnt++
        }
    }
    println(cnt)
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
    val half = b/2
    val tmp = pow(a, half, d)
    //var ret = pow(tmp,2,d)%d
    var ret = (tmp*(tmp/2)%d)+(tmp*(tmp-tmp/2)%d)
    ret %= d
    if(b%2 == 1L) {
        ret = (ret*a)%d
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