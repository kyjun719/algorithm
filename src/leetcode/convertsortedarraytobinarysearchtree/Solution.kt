private class convertsortedarraytobinarysearchtree{
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
        fun sortedArrayToBST(nums: IntArray): TreeNode? {
            return sort(nums, 0, nums.size-1)
        }
        fun sort(nums: IntArray, left: Int, right: Int): TreeNode? {
            if(left > right) {
                return null
            }
            var mid: Int = (left+right)/2
            var node = TreeNode(nums[mid])
            node.left=sort(nums,left,mid-1)
            node.right=sort(nums,mid+1,right)
            //println("${node.left?.`val`}<<${node.`val`}>>${node.right?.`val`}")
            return node
        }
    }
}
