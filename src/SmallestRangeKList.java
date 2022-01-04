import java.util.*;

/*
632. Smallest Range Covering Elements from K Lists
Hard

You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.



Example 1:

Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation:
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].



Note:

    The given list may contain duplicates, so ascending order means >= here.
    1 <= k <= 3500
    -105 <= value of elements <= 105.

Similar to coded but not using TreeSet to avoid equal and remove issue.
public int[] smallestRange(int[][] nums) {
		PriorityQueue<Element> pq = new PriorityQueue<Element>(new Comparator<Element>() {
			public int compare(Element a, Element b) {
				return a.val - b.val;
			}
		});
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			Element e = new Element(i, 0, nums[i][0]);
			pq.offer(e);
			max = Math.max(max, nums[i][0]);
		}
		int range = Integer.MAX_VALUE;
		int start = -1, end = -1;
		while (pq.size() == nums.length) {

			Element curr = pq.poll();
			if (max - curr.val < range) {
				range = max - curr.val;
				start = curr.val;
				end = max;
			}
			if (curr.idx + 1 < nums[curr.row].length) {
				curr.idx = curr.idx + 1;
				curr.val = nums[curr.row][curr.idx];
				pq.offer(curr);
				if (curr.val > max) {
					max = curr.val;
				}
			}
		}

		return new int[] { start, end };
	}

	class Element {
		int val;
		int idx;
		int row;

		public Element(int r, int i, int v) {
			val = v;
			idx = i;
			row = r;
		}
	}


 */
public class SmallestRangeKList {
    class Range implements Comparable<Range> {
        int v;
        int i;
        int numIndex;
        public Range(int val,int ind,int num) {
            v  = val;
            i = ind;
            numIndex = num;
        }

        @Override
        public boolean equals(Object o) {
            // self check
            if (this == o)
                return true;
            // null check
            if (o == null)
                return false;
            // type check and cast
            if (getClass() != o.getClass())
                return false;
            Range r = (Range) o;
            // field comparison
            return (compareTo(r) == 0);
        }



        @Override
        public int compareTo(Range r) {
            return (this.v == r.v) ? ( (this.i == r.i) ? (this.numIndex - r.numIndex):(this.i - r.i)):(this.v - r.v);
        }

    }


    TreeSet<Range> minHeap = new TreeSet<>();


    public int[] smallestRange(List<List<Integer>> nums) {
        int size = Integer.MAX_VALUE;
        int sElem = 0;
        for(int i = 0; i < nums.size(); i++) {
            List<Integer> l = nums.get(i);
            Range r  = new Range(l.get(0),i,0);
            minHeap.add(r);
            //maxHeap.add(r);
        }// End of initialize
        while (true) {
            Range minElem = minHeap.first();
            Range maxElem = minHeap.last();
            int cRange = maxElem.v - minElem.v;
            if ( cRange  < size) {
                size = cRange;
                sElem = minElem.v;
            } else if ((cRange == size)  && ( minElem.v < sElem) ) {
                sElem = minElem.v;
            }
            // Lets remove min element.
            System.out.println(" Min eleme "+minElem.i+ " "+minElem.v+" "+minElem.i);
            if (!minHeap.remove(minElem) )  System.out.println("**** seem not to containg the element");
            //maxHeap.remove(minElem);
            int index = minElem.i; int nInd = minElem.numIndex;
            List<Integer> cList = nums.get(minElem.i) ;
            if ((cList.size() -1) == minElem.numIndex) {
                System.out.println(" Breaking for "+minElem.i);
                break;
            }
            Range r  = new Range(cList.get(minElem.numIndex+1),minElem.i,minElem.numIndex+1);
            if (!minHeap.add(r)) System.out.println("**** seem not to add the element");


        }// End of while
        int[] ret = new int[2];
        ret[0] = sElem;
        ret[1] = sElem+size;
        return ret;
    }
    
    public List<List<Integer>> getList() {
        int[][] iA = 
                {{-89,1,69,89,90,98},
{-43,-36,-24,-14,49,61,66,69},
{73,94,94,96},
{11,13,76,79,90},
{-40,-20,1,9,12,12,14},
{-91,-31,0,21,25,26,28,29,29,30},
{23,88,89},
                        {31,42,42,57},
{-2,6,11,12,12,13,15},
{-3,25,34,36,39},
{-7,3,29,29,31,32,33},
{4,11,14,15,15,18,19},
{-34,9,12,19,19,19,19,20},
{-26,4,47,53,64,64,64,64,64,65},
{-51,-25,36,38,50,54},
{17,25,38,38,38,38,40},
{-30,12,15,19,19,20,22},
{-14,-13,-10,68,69,69,72,74,75},
{-39,42,70,70,70,71,72,72,73},
{-67,-34,6,26,28,28,28,28,29,30,31}};
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < iA.length;i++) {
            List<Integer> cList = new ArrayList<>();
            list.add(cList);
            for(int j = 0; j < iA[i].length;j++) {
                cList.add(iA[i][j]);
            }

        }
        return list;
    }

    public static void main(String[] args) {
        SmallestRangeKList srl = new SmallestRangeKList();

        System.out.println(Arrays.toString(srl.smallestRange(srl.getList())));

    }
/*
[[-89,1,69,89,90,98],
[-43,-36,-24,-14,49,61,66,69],
[73,94,94,96],
[11,13,76,79,90],
[-40,-20,1,9,12,12,14],
[-91,-31,0,21,25,26,28,29,29,30],
[23,88,89],[31,42,42,57],
[-2,6,11,12,12,13,15],
[-3,25,34,36,39],
[-7,3,29,29,31,32,33],
[4,11,14,15,15,18,19],
[-34,9,12,19,19,19,19,20],
[-26,4,47,53,64,64,64,64,64,65],
[-51,-25,36,38,50,54],
[17,25,38,38,38,38,40],
[-30,12,15,19,19,20,22],
[-14,-13,-10,68,69,69,72,74,75],
[-39,42,70,70,70,71,72,72,73],
[-67,-34,6,26,28,28,28,28,29,30,31]]


 */

}
