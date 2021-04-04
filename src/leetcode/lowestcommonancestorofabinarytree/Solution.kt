private class lowestcommonancestorofabinarytree {
    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
    /**
     * Definition for a binary tree node.
     * class TreeNode(var `val`: Int = 0) {
     *     var left: TreeNode? = null
     *     var right: TreeNode? = null
     * }
     */

    class Solution {
        var idxMap: MutableMap<TreeNode,IntArray> = mutableMapOf()
        var list=mutableListOf<TreeNode>()
        var findIdx=0
        fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            traverse(root,0)
            return searchNode(p,q)
        }
        fun traverse(root: TreeNode?, d: Int){
            if(root==null){
                return
            }
            list.add(root!!)
            idxMap[root]=intArrayOf(list.size-1,d)
            //findIdx+=1
            root!!.left?.let{
                traverse(it,d+1)
                list.add(root!!)
            }
            root!!.right?.let{
                traverse(it,d+1)
                list.add(root!!)
            }
        }
        fun searchNode(p: TreeNode?, q: TreeNode?): TreeNode? {
            if(list.size==0){
                return null
            }
            if(p==null || q==null){
                return null
            }
            var r=idxMap[p!!]!!
            var l=idxMap[q!!]!!
            if(r[0]<l[0]){
                var tmp=r
                r=l
                l=tmp
            }
            var minD=minOf(r[1],l[1])
            var rp=r[0]
            var lp=l[0]

            while(rp!=lp){
                if(r[1]>l[1]){
                    minD=minOf(l[1],minD)
                    r=idxMap[list[rp-1]]!!
                    rp-=1
                } else{
                    minD=minOf(r[1],minD)
                    l=idxMap[list[lp+1]]!!
                    lp+=1
                }
                //println("${l.joinToString()}, ${r.joinToString()}>>$lp $rp")
            }
            return list[r[0]]
        }

        fun lowestCommonAncestor2(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            if(root == null || root==p || root==q) {
                return root
            }
            var left = lowestCommonAncestor(root?.left, p, q)
            var right = lowestCommonAncestor(root?.right, p, q)
            if(left != null && right != null) {
                return root
            }
            return if(left==null) right else left
        }
    }
}
