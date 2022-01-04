import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
1296. Divide Array in Sets of K Consecutive Numbers

    User Accepted: 1848
    User Tried: 2411
    Total Accepted: 1890
    Total Submissions: 4750
    Difficulty: Medium

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if its possible otherwise return False.



Example 1:

Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].

Example 2:

Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].

Example 3:

Input: nums = [3,3,2,2,1,1], k = 3
Output: true

Example 4:

Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.


 The idea is that its a multiple of k.
 Also any one number can not occur more then k times.

 it works but the question is for CONSECUTIVE.
 */
public class DivideSetInKArrays {
    /* If not consecutive
    public boolean isPossibleDivide(int[] nums, int k) {
          if (nums.length%k != 0) return false;
          Map<Integer,Integer> mapOfOcc = new HashMap<>();
          for(int i : nums) {
              if (mapOfOcc.get(i) == null) {
                  mapOfOcc.put(i,1);
              } else {
                  int freq = mapOfOcc.get(i)+1;
                  if (freq > k) return false;
                  mapOfOcc.put(i,freq);
              }
          }
          return true;
    }
    */

    /*
    static class Pos {
        int i;
        int f;

        public Pos(int v, int f) {
            i = v; this.f = f;
        }
    }
    */

    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length%k != 0) return false;
        Arrays.sort(nums);
        List<Integer> listOfE = new LinkedList<>();

        int currElement = nums[0];

        int cFreq = 0;
        for(int i = 0;i <  nums.length;i++) {
            if (currElement == nums[i]) {
                cFreq++;
            } else {//new element.
                if ((currElement - nums[i]) > 1) return false;
               // System.out.println(nums[i-1]+" "+cFreq);
                listOfE.add(cFreq);
                cFreq = 1;
                currElement = nums[i];
            }

        }
        listOfE.add(cFreq);
        //Now can we calculate the increasing order.
        Integer[] freqArray = listOfE.stream().toArray(Integer[] ::new);

        int sIndex  = 0;
        int eIndex = Math.min(sIndex+k-1,freqArray.length-1);

        while (eIndex < freqArray.length) {
            if ((sIndex+k-1) > eIndex) return false;
            int nextSIndex = -1;
            for(int j = sIndex; j <= eIndex;j++) {
                if (freqArray[j] == 0) return false;

                freqArray[j] = freqArray[j] -1;
                System.out.print(freqArray[j]+",");
                if ((nextSIndex == -1) && (freqArray[j]!= 0)) nextSIndex = j;
            }
            System.out.println();
            if (nextSIndex == -1) nextSIndex =  sIndex+k;
            sIndex = nextSIndex;
            eIndex = Math.min(sIndex+k-1,freqArray.length-1);
        }

        return true;
    }

    /*
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length%k != 0) return false;
        Arrays.sort(nums);
        List<Pos> listOfE = new LinkedList<>();
        Pos pFirst = new Pos(nums[0],1);
        listOfE.add(pFirst);
        int listIndex = 0;
        for(int i = 1;i <  nums.length;i++) {
            Pos p  = null;
            if (listOfE.get(listIndex).i == nums[i]) {
               p  = listOfE.get(listIndex);
           } else {
               p  = new Pos(nums[i],0);
                listOfE.add(p);
               listIndex++;
           }
           p.f++;
        }
        //Now can we calculate the increasing order.
        listOfE.toArray();

        int sIndex  = 0;
        while(sIndex < listOfE.size()) {
            int nextSIndex = -1; int prevV = -1;
            for(int j = 0; j < k;j++) {
                Pos cPos = listOfE.get(sIndex+j);
                if (cPos.f == 0) return false;
                if ((j != 0) && ((cPos.i - prevV) > 1)) {
                    return false;
                }
                prevV = cPos.i;
                cPos.f--;
                System.out.print(cPos.i+",");
                if ((nextSIndex == -1) && (cPos.f != 0)) nextSIndex = sIndex+j;
            }
            System.out.println();
            if (nextSIndex == -1) nextSIndex =  sIndex+k;
            sIndex = nextSIndex;
        }

        return true;
    }
    */

    /*
          3,4,5,6,20
          15,16,17,18,19
          6,7,8,9,10
          16,17,18,19,20,
     */
    public static void main(String[] args) {
        int[] iA = {15,16,17,18,19,16,17,18,19,20,6,7,8,9,10,3,4,5,6,20};
        int[] iA1 = {3,2,1,2,3,4,3,4,5,9,10,11};
        int[] iA2 = {5,6,7,8,9,6,7,8,9,10,11,12,13,14,15,12,13,14,15,19};
        DivideSetInKArrays daa = new DivideSetInKArrays();

        //System.out.print(daa.isPossibleDivide(iA,5));
        System.out.print(daa.isPossibleDivide(iA1,3));
        //System.out.print(daa.isPossibleDivide(iA2,5));
    }
}
