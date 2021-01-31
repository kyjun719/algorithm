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
	/**
	 해당 노드를 루트로 하는 서브트리의 노드갯수를 저장하지 않음
	 */
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        var leftSize=calcSize(root?.left)
        //println("${root?.`val`} ${k} ${leftSize}")
        if(leftSize==k-1){
            return root!!.`val`
        }
        if(leftSize>=k){
            return kthSmallest(root?.left,k)
        }else{
            return kthSmallest(root?.right,k-leftSize-1)
        }
    }
    fun calcSize(root: TreeNode?): Int {
        if(root==null){
            return 0
        }
        return 1+calcSize(root?.left)+calcSize(root?.right)
    }
	
	/**
	 해당 노드를 루트로 하는 서브트리의 노드갯수를 저장함
	 */
	var sizeMap=mutableMapOf<Int, Int>()
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        return solve(root, k)
    }
    fun solve(root: TreeNode?, k: Int): Int {
        if(root==null){
            return 0
        }
        var leftSize=calcSize(root?.left)
        //println("${root?.`val`} ${leftSize}")
        if(leftSize==k-1){
            return root!!.`val`
        }
        
        if(leftSize>=k){
            return solve(root?.left,k)
        }else{
            return solve(root?.right,k-leftSize-1)
        }
    }
    fun calcSize(root: TreeNode?): Int {
        if(root==null){
            return 0
        }
        if(sizeMap[root!!.`val`]==null){
            sizeMap[root!!.`val`]=1+calcSize(root?.left)+calcSize(root?.right)
        }
        return sizeMap[root!!.`val`]!!
    }
}