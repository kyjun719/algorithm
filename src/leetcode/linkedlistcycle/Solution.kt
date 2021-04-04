private class linkedlistcycle{
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
        fun hasCycle(head: ListNode?): Boolean {
            var set = mutableSetOf<ListNode>()
            var node = head
            while(node != null) {
                if(set.contains(node)) {
                    return true
                }
                set.add(node)
                node = node?.next
            }
            return false
        }

        fun hasCycle2(head: ListNode?): Boolean {
            if(head == null) {
                return false
            }
            var slow=head
            var fast=head.next
            while(slow!=fast) {
                //빨리가는 포인터가 다음갈곳이 없으면 사이클이 없음을 의미함
                if(fast == null || fast?.next==null) {
                    return false
                }
                slow=slow?.next
                fast=fast?.next?.next
            }
            return true
        }
    }
}
