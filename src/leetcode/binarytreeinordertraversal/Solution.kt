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
    fun inorderTraversal(root: TreeNode?): List<Int> {
        var ret=mutableListOf<Int>()
        if(root?.left!=null){
            ret.addAll(inorderTraversal(root?.left))
        }
        if(root!=null){
            ret.add(root!!.`val`)
        }
        if(root?.right!=null){
            ret.addAll(inorderTraversal(root?.right))
        }
        return ret
    }
	
	fun inorderTraversal(root: TreeNode?): List<Int> {
        var ret=mutableListOf<Int>()
        var q=mutableListOf<TreeNode>()
        if(root!=null){
            q.add(root)
        }
        var visited=mutableListOf<Int>()
        while(q.size>0){
            var node=q[q.size-1]
            visited.add(node?.hashCode())
            /*
            for(n in q){
                print("${n.hashCode()} ")
            }
            println()
            */
            if(node.left!=null && !visited.contains(node?.left?.hashCode())){
                q.add(node?.left)
                continue
            }
            ret.add(node!!.`val`)
            q.removeAt(q.size-1)
            if(node?.right!=null && !visited.contains(node?.right?.hashCode())){
                q.add(node?.right)
            }
            //println(ret)
        }
        return ret
    }
}