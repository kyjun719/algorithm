package codejam._2022.qualification.chainreactions

import kotlin.system.exitProcess

data class Node(
    val value: Long,
    var visitCnt: Int = 0,
    var visited: Boolean = false,
    val childList: MutableList<Int> = mutableListOf()
)

fun main() {
    val t = readLine()!!.toInt()
    for(tc in 1..t) {
        val n =  readLine()!!.toInt()
        val nodeList = readLine()!!.split(" ").map { it.toLong() }
        val parentList = readLine()!!.split(" ").map { it.toInt() }

        solution1(tc, n, nodeList, parentList)
        solution2(tc, n, nodeList, parentList)
    }
//    for(n in 1..10) {
//        createParentList(n, List(n) { it.toLong()+1 }, mutableListOf())
//    }
}

fun createParentList(n: Int, nodes: List<Long>, parents: MutableList<Int>) {
    if(parents.size == n) {
        checkAnswerEqual(n, nodes, parents)
        return
    }

    for(p in 0..parents.size) {
        parents.add(p)
        createParentList(n, nodes, parents)
        parents.removeLast()
    }
}

fun checkAnswerEqual(n: Int, nodes: List<Long>, parents: List<Int>) {
//    println("${nodes.joinToString()} >> ${parents.joinToString()}")
    val a1 = solution1(1, n, nodes, parents)
    val a2 = solution2(1, n, nodes, parents)
    if(a1 != a2) {
        println("$n\n${nodes.joinToString()}\n${parents.joinToString()}")
        println("$a2 => $a1")
        exitProcess(0)
    }
}

//dfs
fun solution1(tc: Int, n: Int, nodes: List<Long>, parents: List<Int>): Long {
    val nodeList = nodes.map { Node(value = it) }

    val sumCache = LongArray(n)
    val minCache = LongArray(n)

    val isInitiator = BooleanArray(n) { true }
    val headList = mutableListOf<Int>()
    val parentList = parents.mapIndexed { index, i ->
            val idx = i-1
            if(idx > -1) {
                isInitiator[idx] = false
                nodeList[idx].childList.add(index)
            } else {
                headList.add(index)
            }

            sumCache[index] = nodeList[index].value
            minCache[index] = nodeList[index].value

            idx
        }


    //initiator start in queue
    val q = mutableListOf<Int>()
    isInitiator.forEachIndexed { index, b -> if(b) q.add(index) }

    while(q.isNotEmpty()) {
        val idx = q.removeFirst()
        val now = nodeList[idx]

        //if visitCount != childList.size skip
        if(now.visitCnt != now.childList.size) {
            continue
        }

        if(now.visited) {
            continue
        }

        now.visited = true

        //add parent in queue
        if(parentList[idx] != -1) {
            q.add(parentList[idx])
            nodeList[parentList[idx]].visitCnt++
        }

        val funSum = if(now.childList.size == 0) {
            now.value
        } else {
            //find min child and max compare with now
            var minChild = Long.MAX_VALUE
            var minIdx = -1
            var sum = 0L
            now.childList.forEach {
                if(minChild > minCache[it]) {
                    minChild = minCache[it]
                    minIdx = it
                }
                sum += sumCache[it]
            }
            println("min::$minIdx >> $minChild, sum::$sum")

            minCache[idx] = maxOf(now.value, minChild)

            sum - minChild + maxOf(now.value, minChild)
        }

        println("$idx >> $now :: $funSum")

        //save sum of child plus above compared
        sumCache[idx] = funSum
    }

    //sum saved value with head index
    var sum = 0L
    headList.forEach { sum += sumCache[it] }
    println("Case #$tc: $sum")

    return sum
}

//완탐
fun solution2(tc: Int, n: Int, nodeList: List<Long>, parents: List<Int>): Long {
    val isInitiator = BooleanArray(n) { true }
    val parentList = parents.map {
        val idx = it - 1
        if(idx >= 0) {
            isInitiator[idx] = false
        }
        idx
    }

    val initiatorList = mutableListOf<Int>()
    isInitiator.forEachIndexed { index, b -> if(b) initiatorList.add(index) }

    val sum = startInitiator(BooleanArray(n), initiatorList, nodeList, parentList)
    println("Case #$tc: $sum")

    return sum
}

fun startInitiator(visited: BooleanArray, initiatorList: List<Int>, nodeList: List<Long>, parentList: List<Int>): Long {
    var ret = 0L
    initiatorList.forEach {
        if(visited[it]) return@forEach
        val tmpVisited = visited.copyOf()
        var tmp = move(it, tmpVisited, nodeList, parentList)
        tmp += startInitiator(tmpVisited, initiatorList, nodeList, parentList)

//        println("${tmpVisited.joinToString()} $it >> $tmp")

        ret = maxOf(ret, tmp)
    }

    return ret
}

fun move(start: Int, visited: BooleanArray, nodeList: List<Long>, parentList: List<Int>): Long {
    var ret = 0L
    var ptr = start
    while (true) {
        ret = maxOf(ret, nodeList[ptr])
        visited[ptr] = true
        ptr = parentList[ptr]
        if(ptr == -1) {
            break
        }
        if(visited[ptr]) {
            break
        }
    }
    return ret
}