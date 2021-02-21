/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    data class Node(val v: Int, val node: TreeNode?)
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        var ret=mutableListOf<MutableList<Int>>()
        var q=mutableListOf<Node>()
        q.add(Node(0,root))
        while(q.size!=0){
            var tmp=q.removeAt(0)
            if(tmp.node==null){
                continue
            }
            if(ret.size<=tmp.v){
                ret.add(mutableListOf<Int>())
            }
            ret[tmp.v].add(tmp.node!!.`val`)
            tmp.node?.left?.let {
                q.add(Node(tmp.v+1,it))
            }
            tmp.node?.right?.let {
                q.add(Node(tmp.v+1,it))
            }
        }
        return ret
    }
}