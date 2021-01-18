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
    fun isPalindrome(head: ListNode?): Boolean {
        var tail = head
        var mid = head
        //find center
        while(true) {
            if(tail?.next == null) {
                break
            }
            if(tail?.next?.next==null){
                mid=mid?.next
                break
            }
            mid=mid?.next
            tail=tail?.next?.next
        }
        var f=head
		//중간부터 끝까지 역순으로 연결함
        mid=rev(mid)
        //println("${mid?.`val`} ${head?.`val`}")
        while(mid!=null) {
            if(mid?.`val` != f?.`val`) return false
            mid=mid?.next
            f=f?.next
        }
        return true
    }
    fun rev(mid: ListNode?): ListNode?{
        var prev: ListNode? = null
        var node = mid
        while(node != null) {
			//prev -> node -> next를 next -> node -> prev로 바꿔야함
            var next = node?.next
			//node -> next를 prev로 바꿈
            node?.next=prev
			//node->next->next는 역순으로 하기위해 node가 되어야함
            prev=node
            node=next
        }
        return prev
    }
}