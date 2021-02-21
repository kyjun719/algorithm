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
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if(preorder.size==0){
            return null
        }
        //println(preorder.joinToString())
        //println(inorder.joinToString())
        var root=TreeNode(preorder[0])
        var rootIdx=inorder.indexOf(preorder[0])
        
        root.left=buildTree(
                preorder.copyOfRange(1,1+rootIdx),
            inorder.copyOfRange(0,rootIdx))
            
        root.right=buildTree(
            preorder.copyOfRange(rootIdx+1,preorder.size),
                inorder.copyOfRange(rootIdx+1,inorder.size)
        )
        return root
    }
}