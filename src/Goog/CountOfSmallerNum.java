package Goog;

import java.util.Arrays;
import java.util.*;
public class CountOfSmallerNum {
    /*
    class Pos implements Comparable<Pos> {
        int v; int i;
        public Pos(int va, int ii) {
            v = va; i = ii;
        }

        @Override
        public int compareTo(Pos p) {
            return this.v - p.v;
        }

        // Impelements equals to

    }

    public List<Integer> countSmaller(int[] nums) {
        if ( ( nums == null) || (nums.length == 0) ) return null;
        List<Integer> res = new LinkedList<>();

        Pos[] rA = new Pos[nums.length];
        Integer[] A = new Integer[nums.length];
        Arrays.fill(A,0);
        for(int i = 0; i < nums.length; i++) {
            rA[i] = new Pos(nums[i],i);
        }

        solve(rA,A);
        res.addAll(Arrays.asList(A));
        return res;
    }

    private Pos[] solve(Pos[] rA,Integer[] A) {
        int size = rA.length;
        if (size <= 1) return rA;
        int mid = size/2;
        Pos[] lArr = solve(Arrays.copyOfRange(rA,0,mid),A);
        Pos[] rArr = solve(Arrays.copyOfRange(rA,mid,size),A);

        //solve(lArr,A);
        //solve(rArr,A);
        //int aIndex = 0;
        int l = 0; int r = 0;


        while (  ( l < lArr.length) || (r < rArr.length) ) {
          if ( (r == rArr.length) || ( (l < lArr.length) && (lArr[l].v <= rArr[r].v)) ) {// copy left
              rA[l+r] = lArr[l];
              A[lArr[l].i] += r;
              l++;
          } else {
              rA[l+r] = rArr[r];
              r++;
          }
        }
        return rA;
    }



    //Working but time limit exceeded.
    public List<Integer> countSmaller(int[] nums) {
        TreeSet<Integer> tSet = new TreeSet<>();
        Map<Integer,Integer> map = new HashMap<>();
        Integer[] R = new Integer[nums.length];
        for(int i = nums.length-1;i >= 0; i--) {
            Set<Integer> s1 = tSet.headSet(nums[i]);
            int S = s1.size();
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            tSet.add(nums[i]);
            Set<Integer> keySet = new HashSet<>(map.keySet());
            keySet.retainAll(s1);
            for(Integer e : s1) {
                if (map.get(e) == 1) continue;
                S += (map.get(e)-1);
            }
            R[i] = S;
        }
        return Arrays.asList(R);
    }

     */

    class P {
        int V;
        int pos;// basically P is moving everywhere. However based on P we need to update pos which does not change.
    }
    P[] PA= null;
    int[] res = null;
    P[] temp = null;
    public List<Integer> countSmaller(int[] nums) {
        res = new int[nums.length];
        PA = new P[nums.length];
        temp = new P[nums.length];
        for(int i = 0; i < nums.length;i++)  {
            P pElem  = new P();
            pElem.pos = i; pElem.V = nums[i];
            PA[i] = pElem;
        }

        mergeSort(PA,0,PA.length);
        List<Integer> lis = new ArrayList<>();
        for(int p : res) {
            lis.add(p);
        }
        return lis;
    }

    public void mergeSort(P[] A,int s,int e) { // s inclusive. e exclusive.
        if (s >= (e-1)) return;
        int mid = (s+e)/2;
        mergeSort(A,s,mid);
        mergeSort(A,mid,e);
        int f = s; int sec = mid;
        int small = 0;
        for(int i = s; i < e;i++) {
            if (get(A,f,mid) <= get(A,sec,e)) {
                res[A[f].pos] += small;
                temp[i] = A[f++];
            } else {
                temp[i] = A[sec++];
                small++;
            }
        }
        for(int i = s; i < e;i++) {
            A[i] = temp[i];
        }
    }

    public int get(P[] A,int pos,int last) {
        if (pos >= last) return Integer.MAX_VALUE;
        return A[pos].V;
    }




    public static void main(String[] args) {
        CountOfSmallerNum csn = new CountOfSmallerNum();
        int[] A = {26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
        //int[] A = {4,5,8,2,3,2};
        List<Integer> ll = csn.countSmaller(A);
        ll.forEach(e->System.out.print(e+" "));
        System.out.println();
        //System.out.println(A[A.length-24]);
        System.out.println(Arrays.toString(A));
    }

}
