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
    fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
        var nodeA: ListNode? = headA
        while(nodeA!=null){
            var nodeB: ListNode? = headB
            while(nodeB!=null){
                //println("${nodeA.`val`} ${nodeB.`val`}")
                //println("${nodeA==nodeB} ${nodeA.equals(nodeB)}")
                if(nodeA==nodeB){
                    return nodeA
                }
                nodeB=nodeB.next
            }
            nodeA=nodeA.next
        }
        return null
    }
	
	fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
        var a=headA
        var b=headB
        while(a != b) {
            a = if(a==null)headB else a.next
            b = if(b==null)headA else b.next
        }
        return a
    }
}