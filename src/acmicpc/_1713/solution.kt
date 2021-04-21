package acmicpc._1713

data class Candidate(val idx: Int, val num: Int, var cnt: Int)
fun main() {
    var n = readLine()!!.toInt()
    var m = readLine()!!.toInt()
    var arr = readLine()!!.split(" ").map { it.toInt() }
    var list = mutableListOf<Candidate>()
    for(i in 0..m-1) {
        var num = arr[i]
        var isIn = false
        for(tmp in list) {
            if(tmp.num == num) {
                tmp.cnt+=1
                isIn=true
                break
            }
        }

        if(!isIn) {
            if(list.size < n) {
                list.add(Candidate(i,num,1))
            } else {
                list.removeAt(list.size-1)
                list.add(Candidate(i,num,1))
            }
        }

        //println(list)
        list.sortWith(Comparator {o1, o2 ->
            if(o1.cnt != o2.cnt) {
                o2.cnt-o1.cnt
            } else {
                o2.idx-o1.idx
            }
        })
        //println(list)
        //println("==========================")
    }
    var str = ""
    list.sortWith(Comparator {o1,o2 ->
        o1.num-o2.num
    })
    //println(list)
    list.map{ str+=it.num.toString()+" "}
    println(str.substring(0,str.length-1))
}