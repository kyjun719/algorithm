private class binarytreezigzaglevelordertraversal{
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
        fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
            var ret=mutableListOf<MutableList<Int>>()
            var q=mutableListOf<TreeNode?>(root)
            var lf=false
            while(q.size>0){
                var list=mutableListOf<Int>()
                var next=mutableListOf<TreeNode>()
                while(q.size>0){
                    var tmp=q.removeAt(0)
                    if(tmp==null){
                        continue
                    }
                    //print("${tmp?.`val`} >> ")
                    list.add(tmp?.`val`!!)
                    if(lf){
                        tmp?.right?.let{next.add(0,it)}
                        tmp?.left?.let{next.add(0,it)}
                    }else{
                        tmp?.left?.let{next.add(it)}
                        tmp?.right?.let{next.add(it)}
                    }
                }
                if(!lf){
                    next=next.asReversed()
                }

                lf=lf xor true
                if(list.size>0){
                    ret.add(list)
                }
                q.addAll(next)
            }
            return ret
        }
    }
}
