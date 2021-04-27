package acmicpc._1062

var k = 0
var charList = mutableListOf<Char>()
var wordList = mutableListOf<Int>()
fun main() {
    var nk = readLine()!!
    var n = nk.split(" ")[0].toInt()
    k = nk.split(" ")[1].toInt()
    if (k < 5) {
        println(0)
        return
    }

    charList.addAll("antic".toList())
    for(i in 0..n-1) {
        var tmp = readLine()!!
        wordList.add(convStr(tmp.substring(4,tmp.length-4)))
        for(ch in tmp.substring(4,tmp.length-4)) {
            if(charList.contains(ch)) {
                continue
            }
            charList.add(ch)
        }
    }
//    println(charList)

    var selected = IntArray(charList.size)
    for(i in 0..4) {
        selected[i]=1
    }
    println(solve(5,selected,5))
}

fun convStr(str: String) : Int {
    var ret=0
    for(ch in str){
        ret = ret or (1 shl (ch-'a'))
    }
    return ret
}

fun solve(idx: Int, selected: IntArray, selNum: Int) : Int {
    if(selNum==k || idx==selected.size) {
        return calc(selected)
    }
    var ret = 0
    for(i in idx..selected.size-1) {
        selected[i]=1
        ret = maxOf(ret, solve(i+1, selected, selNum+1))
        selected[i]=0
    }
    return ret
}

fun calc(selected: IntArray) : Int {
    var selStr = ""
    for(i in 0..selected.size-1) {
        if(selected[i]==1) {
            selStr += charList[i]
        }
    }
    var selNum = convStr(selStr)
//    println("$selStr >> ${selNum.toString(2)}")
    var ret = 0
    for(i in wordList) {
        if((selNum and i) == i) {
            ret += 1
        }
    }
    return ret
}