private class mergetwosortedlists{
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
        fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
            var root: ListNode? = null
            if(l1 != null || l2 != null) {
                if(l2==null || (l1!=null && l1.`val`<l2.`val`)) {
                    root=l1
                    root?.next=mergeTwoLists(l1?.next,l2)
                } else {
                    root=l2
                    root?.next=mergeTwoLists(l1,l2?.next)
                }
            }
            //println("${l1?.`val`} ${l2?.`val`} -> ${root?.`val`}")
            return root
        }
        fun mergeTwoLists2(l1: ListNode?, l2: ListNode?): ListNode? = when {
            l1 == null -> l2
            l2 == null -> l1
            l1.`val` < l2.`val` -> l1.apply { next = mergeTwoLists(l1.next, l2) }
            else -> l2.apply { next = mergeTwoLists(l1, l2.next) }
        }
    }
}
