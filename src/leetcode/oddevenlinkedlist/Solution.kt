private class oddevenlinkedlist{
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
        fun oddEvenList(head: ListNode?): ListNode? {
            var even: ListNode? = head
            var odd: ListNode? = head?.next
            var oddhead: ListNode? = head?.next
            var node=head?.next?.next
            var isEven=true
            while(node!=null){
                if(isEven){
                    even?.next=node
                    even=node
                }else{
                    odd?.next=node
                    odd=node
                }
                isEven=!isEven
                node=node?.next
            }
            odd?.next=null
            even?.next=oddhead
            /*
            var tmp=head
            while(tmp!=null){
                println(tmp?.`val`)
                tmp=tmp?.next
            }
            */
            return head
        }
    }
}
