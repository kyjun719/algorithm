package acmicpc._20055

lateinit var arr: IntArray
var n=0
var k=0
var n2 = 0
fun main(){
    var tmp = readLine()!!.split(" ").map{ it.toInt() }.toIntArray()
    n=tmp[0]
    k=tmp[1]
    arr = readLine()!!.split(" ").map{ it.toInt() }.toIntArray()
    n2 = n*2
    println(solve())
}

fun solve(): Int {
    var onIdx = 0
    var offIdx = n-1
    var robot = BooleanArray(n)
    var cnt=1
    while(true){
        //move container
        onIdx = nextIdx(onIdx, false)
        offIdx = nextIdx(offIdx, false)
        for(i in n-1 downTo 1) {
            robot[i] = robot[i-1]
        }
        robot[0] = false
        robot[n-1] = false

        //move robot
        var idx = offIdx
        for(i in n-1 downTo 1) {
            if(!robot[i] && robot[i-1] && arr[idx]>0) {
                arr[idx]-=1
                robot[i] = true
                robot[i-1] = false
            }
            idx = nextIdx(idx, false)
        }
        robot[n-1] = false

        //load
        if(arr[onIdx]>0){
            arr[onIdx]-=1
            robot[0]=true
        }

        //check
        if(k <= arr.count{ it==0 }) break
        cnt+=1
    }
    return cnt
}

fun nextIdx(i: Int, isForward: Boolean): Int {
    return (i+n2+(if(isForward) 1 else -1))%n2
}