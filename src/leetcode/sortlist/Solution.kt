private class sortlist{
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     */
    class Solution {
        fun printNode(node: ListNode?) {
            var n=node
            while(n!= null) {
                print("${n?.`val`} ")
                n=n?.next
            }
            println()
        }
        fun sortList(head: ListNode?): ListNode? {
            if(head == null || head?.next==null) {
                return head
            }
            var mid=split(head)
            var left=sortList(head)
            var right=sortList(mid)
            //printNode(left)
            //printNode(right)
            var ret= merge(left,right)
            //printNode(ret)
            //println("==================")
            return ret
        }

        fun split(head: ListNode): ListNode? {
            var a: ListNode? = head
            var b: ListNode? = head
            while(b?.next?.next != null) {
                a=a?.next
                b=b?.next?.next
            }
            var mid=a?.next
            a?.next=null
            return mid
        }

        fun merge(left: ListNode?, right: ListNode?) : ListNode? {
            var l = left
            var r = right
            var head: ListNode?
            if(l==null || (r!=null && l!!.`val`>r!!.`val`)) {
                head=r
                r=r?.next
            } else {
                head=l
                l=l?.next
            }
            var node=head
            while((l!=null) && (r!=null)) {
                if(l==null || l!!.`val`>r!!.`val`) {
                    node?.next=r
                    r=r?.next
                } else {
                    node?.next=l
                    l=l?.next
                }
                node=node?.next
            }
            while(l!=null) {
                node?.next=l
                l=l?.next
                node=node?.next
            }
            while(r!=null) {
                node?.next=r
                r=r?.next
                node=node?.next
            }

            return head
        }
    }
}
