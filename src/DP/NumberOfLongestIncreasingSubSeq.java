package DP;

/*
673. Number of Longest Increasing Subsequence
Medium

Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:

Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].

Example 2:

Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.

Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
Accepted
43,180
Submissions

 */
public class NumberOfLongestIncreasingSubSeq {
    /*  Seem to be good solution for LIS.
    public int findNumberOfLIS(int[] nums) {
        //Comparator<Integer> comp =(a,b)->(Integer.compare(nums[a],nums[b]) != 0)?(Integer.compare(nums[a],nums[b])):(b-a) ;
        Comparator<Integer> comp =(a,b)->Integer.compare(nums[a],nums[b]) ;
        TreeSet<Integer> S = new TreeSet<>(comp);
        int n = nums.length;
        int[] LE = new int[n];//Lit of max length for index i.
        int[] CE = new int[n];//count.
        int maxLIS = 1;
        for(int i = 0; i < n; i++) {

            Set<Integer> floor = S.headSet(i,true);
            if ((floor != null) && (floor.size() != 0)) {
                for (Integer small : floor) {
                      if (LE[i] <  LE[small]) {
                          LE[i] = LE[small];
                          CE[i] = 1;
                      } else if (LE[i] == LE[small]) {
                          CE[i]++;
                      }
                }
                LE[i]++; //self.
            } else {
                CE[i] = 1; LE[i] = 1;
            }
            S.add(i);
            maxLIS = Math.max(LE[i],maxLIS);
        }
        int f = 0;
        for(int i = n-1; i >= 0; i--) {
            if (LE[i] == maxLIS) return CE[i];
        }
        return 1;
        //return f;
    }
    */



    public int findNumberOfLIS(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;
        int N = nums.length;
        int[] C  = new int[N];// Count till now.
        int[] L = new int[N];// Length till now.
        int mL = 1; int mC =1;
        C[0] = 1; L[0] = 1;
        for(int i = 1 ; i < N; i++) {
            int cElem = nums[i]; L[i] = 1;C[i] = 1;
            for(int j = 0 ; j < i; j++) {
                  if (cElem > nums[j]) {
                      if (L[i] < (L[j]+1)) {
                          L[i] = L[j]+1;
                          C[i] = C[j];
                      } else if (L[i] == (L[j]+1)) {
                          C[i] += C[j];
                      }
                  }
            }

            if (mL < L[i]) {
                mL = L[i];
                mC = C[i];
            } else if (mL == L[i]) {
                mC += C[i];
            }
        }
        return mC;

    }

    public int findNumberOfLIS2(int[] nums) {
        int n = nums.length, res = 0, max_len = 0;
        int[] len =  new int[n], cnt = new int[n];
        for(int i = 0; i<n; i++){
            len[i] = cnt[i] = 1;
            for(int j = 0; j <i ; j++){
                if(nums[i] > nums[j]){
                    if(len[i] == len[j] + 1)cnt[i] += cnt[j];
                    if(len[i] < len[j] + 1){
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }
            if(max_len == len[i])res += cnt[i];
            if(max_len < len[i]){
                max_len = len[i];
                res = cnt[i];
            }
        }
        return res;
    }



    public static void main(String[] args) {
        int[] A = {1,1,1,2,2,2,3,3,3};
        NumberOfLongestIncreasingSubSeq nl = new NumberOfLongestIncreasingSubSeq();
        System.out.println(nl.findNumberOfLIS(A));

    }

}
