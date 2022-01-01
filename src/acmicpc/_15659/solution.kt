package acmicpc._15659

const val PLUS = 0
const val MINUS = 1
const val MUL = 2
const val DIV = 3

fun main(){
    val n = readLine()!!.toInt()
    val numArr = readLine()!!.replace("\n", "").split(" ").map { it.toInt() }.toIntArray()

    val opArr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    val ret = solve(numArr[0].toString(), 1, numArr, opArr)
    println("${ret.second}\n${ret.first}")
}

fun solve(eq: String, idx: Int, numArr: IntArray, opArr: IntArray): Pair<Int, Int> {
    if(idx == numArr.size) {
        return calc(eq)
    }

    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE

    for(i in 0 until opArr.size) {
        if(opArr[i] > 0) {
            opArr[i] -= 1
            val ret = solve(createEquation(eq, i, numArr[idx]), idx+1, numArr, opArr)
            min = minOf(min, ret.first)
            max = maxOf(max, ret.second)
            opArr[i] += 1
        }
    }

    return Pair(min, max)
}

fun createEquation(eq: String, opIdx: Int, num: Int): String {
    return eq + when(opIdx) {
        PLUS -> "+"
        MINUS -> "-"
        MUL -> "*"
        DIV -> "/"
        else -> ""
    } + num
}

fun calc(eq: String): Pair<Int, Int> {
    val postfixEq = mutableListOf<String>()
    val opStack = mutableListOf<Char>()
    var idx = 0
    var numStartIdx = -1
    while(idx < eq.length) {
        if(isOp(eq[idx])) {
            if(numStartIdx != -1) {
                postfixEq.add(eq.substring(numStartIdx,idx))
                numStartIdx = -1
            }

            val beforeOp = opStack.lastOrNull()?.let { getOpPriority(it) } ?: 0
            val nowOp = getOpPriority(eq[idx])
            if(eq[idx] == '(' || nowOp > beforeOp) {
                opStack.add(eq[idx])
            } else if(eq[idx] == ')') {
                while(opStack.isNotEmpty()) {
                    if(opStack.last() == '(') {
                        opStack.removeLast()
                        break
                    }
                    postfixEq.add(opStack.removeLast().toString())
                }
            } else {
                while(opStack.isNotEmpty()) {
                    val nextOp = getOpPriority(opStack.last())
                    if(nextOp < nowOp) {
                        break
                    }
                    postfixEq.add(opStack.removeLast().toString())
                }
                opStack.add(eq[idx])
            }
        } else {
            if(numStartIdx == -1) {
                numStartIdx = idx
            }
        }
        idx++
//        println("$idx >> $postfixEq , $opStack")
    }

    if(numStartIdx != -1) {
        postfixEq.add(eq.substring(numStartIdx))
    }
    while(opStack.isNotEmpty()) {
        postfixEq.add(opStack.removeLast().toString())
    }

//    println(postfixEq)

    val reqStack = mutableListOf<Int>()
    postfixEq.forEach {
        if(isOp(it)) {
            val a = reqStack.removeLast()
            val b = reqStack.removeLast()
            reqStack.add(calcOp(b,a,it))
        } else {
            reqStack.add(it.toInt())
        }
//        println(reqStack)
    }

//    println(reqStack)
    return Pair(reqStack[0], reqStack[0])
}

fun isOp(c: Char): Boolean {
    val opArr = arrayOf('(', ')', '+','-','*','/')
    return c in opArr
}

fun isOp(s: String): Boolean {
    if(s.length == 1) {
        return isOp(s.toCharArray()[0])
    }
    return false
}

fun calcOp(a: Int, b: Int, op: String): Int {
    return when(op) {
        "+" -> a+b
        "-" -> a-b
        "*" -> a*b
        "/" -> a/b
        else -> 0
    }
}

fun getOpPriority(op: Char): Int {
    return when(op) {
        '(', ')' -> 0
        '+','-' -> 1
        '*','/' -> 2
        else -> 3
    }
}
