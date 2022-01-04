import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class KSmallest {
    // # // # //# arrs =
// # // # //#  [
// # // # //#    [1, 4, 7, 9],
// # // # //#    [0, 3],
// # // # //#    [2, 5, 10],
// # // # //#  ]
// # // # //# kth smallest elment k=3 -> 2

// # M sorted arrays/lists
// # N total elements
// # avg L elements per array/list

//  M -  N Log N


// heap
/*
    Each pass : M arrays--> Pushed to a heap of size M
           There will be K pass

           KM log M

*/



        class ArrayElem {
            int v;
            int i;
            public ArrayElem(int v,int i) {
                this.v = v;this.i = i;

            }

        }
        Comparator<ArrayElem> comp = (a, b)->(a.v-b.v);
        PriorityQueue<ArrayElem> heap = new PriorityQueue<>(comp);

        /* Assume that list will not be null nor the child list. Also total element will be greater or equal to k */
        public int findK(List<List<Integer>> list,int k) {
            int[] index = new int[list.size()];
            populateHeap(index,heap,list);
            int kMin;
            int iter = 0;
            ArrayElem aElem = null;
            // K * M
            while (iter < k) {
                aElem = heap.poll();
                int cIndex = aElem.i;
                index[cIndex] += 1;
                iter++;
                Integer cInt = getElement(list.get(cIndex),index[cIndex]);
                if (cInt == null) continue;
                ArrayElem cElem = new ArrayElem(cInt,cIndex);
                heap.add(cElem);

            }
            kMin = aElem.v;
            return kMin;

        }


        private void populateHeap(int[] ind,PriorityQueue<ArrayElem> heap,List<List<Integer>> list) {
            for(int i = 0; i < list.size();i++ ) {
                List<Integer> cList = list.get(i);
                Integer cInt = getElement(cList,ind[i]);
                if (cInt == null) continue;
                ArrayElem cElem = new ArrayElem(cInt,i);
                ind[i] += 1;
                heap.add(cElem);
            }
        }

        private Integer getElement(List<Integer> cList,int i) {
            if (i < cList.size() ) return cList.get(i);
            return null;
        }

        public static void main(String[] args) {
            // # // # //#    [1, 4, 7, 9],
// # // # //#    [0, 3],
// # // # //#    [2, 5, 10],
            List<List<Integer>> ll = new LinkedList<>();
            List<Integer> l1 = new LinkedList<>();
            l1.add(1); l1.add(4); l1.add(7);l1.add(9);
            ll.add(l1);
            List<Integer> l2 = new LinkedList<>();
            l2.add(0); l2.add(3);
            ll.add(l2);
            List<Integer> l3 = new LinkedList<>();
            l3.add(2); l3.add(5); l3.add(10);
            ll.add(l3);
            KSmallest ks  = new KSmallest();
            System.out.println(ks.findK(ll,3));

        }













}
