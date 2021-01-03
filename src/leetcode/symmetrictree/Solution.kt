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
    fun isSymmetric(root: TreeNode?): Boolean {
        return isEqual(root?.left, root?.right)
    }
    fun isEqual(node1: TreeNode?, node2: TreeNode?): Boolean {
        if(node1 == null || node2 == null) {
            return node1==node2
        }
        return isEqual(node1.left, node2.right) && node1.`val`==node2.`val` &&
				isEqual(node1.right, node2.left)
    }
	fun isSymmetric2(root: TreeNode?): Boolean {        
        var q: Queue<TreeNode?> = LinkedList<TreeNode?>()
        q.add(root?.left)
        q.add(root?.right)
        while(!q.isEmpty()) {
            var a = q.poll()
            var b = q.poll()
            // println("${a?.`val`} ${b?.`val`} >> ${a==b}")
            if(a?.`val` != b?.`val`) {
                return false
            }
            if(a!=null && b!= null) {
                q.add(a?.left)
                q.add(b?.right)
                q.add(a?.right)
                q.add(b?.left)   
            }
        }
        return true
    }
}