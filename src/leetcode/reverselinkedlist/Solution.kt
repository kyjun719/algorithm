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
    fun reverseList(head: ListNode?): ListNode? {
        var tail: ListNode? = null
        var node = head
        while(true) {
            var next = node?.next
            if(node == null) break
            node?.next=tail
            tail = node
            node = next
        }
        return tail
    }
	
 	//재귀
	fun reverseList2(head: ListNode?): ListNode? {
        if(head?.next == null) {
            return head
        }
        var result=reverseList(head?.next)
        head?.next?.next=head
        head?.next=null
        return result
    }
}