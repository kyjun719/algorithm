private class maximumdepthofbinarytree{
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
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
        fun maxDepth(root: TreeNode?): Int {
            return if(root==null) 0 else maxOf(maxDepth(root.left), maxDepth(root.right))+1
        }
    }
}
