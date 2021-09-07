package programmers.separatetestingroom

class Solution {
    data class Node(
        var num: Int = 0,
        var left: Int = -1,
        var right: Int = -1
    )
    lateinit var arr: Array<Node>
    lateinit var parent: IntArray
    var sum = 0
    var root = 0
    var nk = 0
    fun solution(k: Int, num: IntArray, links: Array<IntArray>): Int {
        nk = k
        arr = Array<Node>(num.size){Node()}
        parent = IntArray(num.size){ i -> i }

        num.forEachIndexed{i,v ->
            arr[i].num=v
            sum+=v
        }
        links.forEachIndexed{i,child->
            if(child[0]!=-1) {
                arr[i].left=child[0]
                parent[child[0]] = i
            }
            if(child[1]!=-1) {
                arr[i].right=child[1]
                parent[child[1]] = i
            }
        }

        for(i in 0 until parent.size) {
            if(i == parent[i]) {
                root = i
                break
            }
        }

        return solve()
    }

    fun solve(): Int {
        var l = sum/arr.size
        var h = sum
        while(l < h) {
            //println("$l, $h")
            val mid = (l+h)/2
            if(separateEnable(mid)) {
                h = mid
            } else {
                l = mid+1
            }
        }
        return (l+h)/2
    }

    val failVal = Pair(-1, Int.MAX_VALUE)
    fun calcChildGroup(idx: Int, m: Int): Pair<Int, Int> {
        if(idx == -1) {
            return Pair(0, Int.MAX_VALUE)
        }
        val n = arr[idx]
        if(n.left == -1 && n.right == -1) {
            return if(n.num <= m) {
                Pair(1, n.num)
            } else {
                failVal
            }
        }

        val l: Pair<Int, Int> = calcChildGroup(n.left, m)
        val r: Pair<Int, Int> = calcChildGroup(n.right, m)
        //println("$idx>> l::$l, r::$r")
        if(l.first == -1 || r.first == -1) {
            return Pair(-1, Int.MAX_VALUE)
        }

        if(l.first != 0 && r.first != 0 && n.num+l.second+r.second <= m) {
            return Pair(l.first+r.first-1, n.num+l.second+r.second)
        } else if((l.first != 0 && n.num+l.second <= m) || (r.first != 0 && n.num+r.second <= m)) {
            return Pair(l.first+r.first, n.num+minOf(l.second,r.second))
        } else if(n.num <= m) {
            return Pair(l.first+r.first+1, n.num)
        } else {
            return failVal
        }
    }

    fun separateEnable(m: Int): Boolean {
        val rp = calcChildGroup(root,m)
        //println("$m, root::$rp")
        return rp.first != -1 && rp.first <= nk
    }
}