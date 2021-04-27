package acmicpc._1339

lateinit var strList : MutableList<String>
lateinit var chArr : Array<Char>
fun main(){
    var n = readLine()!!.toInt()
    strList = mutableListOf<String>()
    var chSet=hashSetOf<Char>()
    for(i in 0..n-1){
        var tmp=readLine()!!
        chSet.addAll(tmp.toCharArray().toList())
        strList.add(tmp)
    }
    chArr = chSet.toTypedArray()
    println(solve(0,IntArray(10),IntArray(chArr.size)))
}

fun solve(idx: Int, numArr: IntArray, arr: IntArray) : Int {
    if(idx==arr.size){
        return calc(arr)
    }
    var ret=0
    for(i in 9 downTo 0){
        if(numArr[i]==0){
            numArr[i]=1
            arr[idx]=i
            ret = maxOf(ret,solve(idx+1,numArr,arr))
            arr[idx]=0
            numArr[i]=0
        }
    }
    return ret
}

fun calc(arr: IntArray) : Int {
    var ret=0
    for(str in strList){
        var tmp=0
        for(ch in str){
            tmp*=10
            tmp+=arr[chArr.indexOf(ch)]
        }
        ret+=tmp
    }
    //println(ret)
    return ret
}