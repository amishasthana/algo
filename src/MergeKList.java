/*
23. Merge k Sorted Lists

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6



 */
public class MergeKList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    public ListNode mergeKLists(ListNode[] lists) {
        ListNode listRes = null;
        if ((lists == null) || (lists.length == 0)) {
            return listRes;
        }
        ListNode[] cHeaders = new ListNode[lists.length];
        int i = 0;
        for(ListNode h : lists) {
            cHeaders[i++] = h;
        }
        ListNode cNode = null;
        while(true) {
            ListNode nextNode = getNext(cHeaders);
            if (nextNode == null) break;//All done
            if (listRes == null) {
                listRes = nextNode;
                cNode = nextNode;
                continue;
            }
            cNode.next = nextNode;
            cNode = nextNode;

        }
        return listRes;
    }

    private ListNode getNext(ListNode[] cHeaders) {
        ListNode retNode = null;
        int minV = Integer.MAX_VALUE;
        int indexMin = 0;

        for(int i = 0;i < cHeaders.length;i++ ) {
            if (cHeaders[i] == null) continue;
            if (cHeaders[i].val < minV) {
                minV = cHeaders[i].val;
                retNode = cHeaders[i];
                indexMin = i;
            }
        }
        if (retNode != null) {
            ListNode newNode = new ListNode(retNode.val);
            cHeaders[indexMin] = cHeaders[indexMin].next;// Increment the header
            retNode = newNode;
        }

        return retNode;
    }
}
