package Goog;

import java.util.PriorityQueue;

public class MergeKList {

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
   }
    
    class Pos implements Comparable<Pos> {
        int v;
        //int pos;
        ListNode lis;
        public Pos(ListNode l) {
            v = l.val;
        }

        @Override
        public int compareTo(Pos p) {
            return v - p.v;
        }

        @Override
        public boolean equals(Object o) {
            if ( o == null) return false;
            if ( o == this ) return true;
            if ( this.getClass() != o.getClass() ) return false;
            Pos  p = (Pos)o;
            return ( compareTo(p) == 0);
        }
    }



    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        ListNode listRes = null;
        if ((lists == null) || (lists.length == 0)) {
            return listRes;
        }

        int i = 0;
        for(ListNode h : lists) {
            if (h != null) pq.add(new Pos(h));
        }
        ListNode cNode = null;
        while(!pq.isEmpty()) {
            ListNode nextNode = pq.poll().lis;
            ListNode temp = nextNode.next;
            if (temp != null) {
                pq.add(new Pos(temp));
            }
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





}
