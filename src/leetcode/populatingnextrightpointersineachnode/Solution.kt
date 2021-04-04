private class populatingnextrightpointersineachnode{
    class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
        var next: Node? = null
    }
    /**
     * Definition for a Node.
     * class Node(var `val`: Int) {
     *     var left: Node? = null
     *     var right: Node? = null
     *     var next: Node? = null
     * }
     */
    class Solution {
        fun connect(root: Node?): Node? {
            if(root==null){
                return root
            }
            var q=mutableListOf<Node>(root!!)
            while(q.size>0){
                var next=mutableListOf<Node>()
                var p: Node? = null
                while(q.size>0){
                    var tmp=q.removeAt(0)
                    tmp.left?.let{
                        p?.let{
                            it.next=tmp.left
                        }
                        p=it
                        next.add(it)
                    }
                    tmp.right?.let{
                        p?.let{
                            it.next=tmp.right
                        }
                        p=it
                        next.add(it)
                    }
                }
                q.addAll(next)
            }
            return root
        }
    }
}
