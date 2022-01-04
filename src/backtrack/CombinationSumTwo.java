package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
40. Combination Sum II
Medium

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]


 */
public class CombinationSumTwo {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> L = new ArrayList<>();
        Arrays.sort(candidates);
        backTrack(candidates,L,new ArrayList<>(),target,0);
        return L;
    }

    private void backTrack(int[] candidates,List<List<Integer>> L, List<Integer> T,int target,int s) {
        if ( target == 0) {
            L.add(new ArrayList<>(T));
            return;
        } else {
            for(int i = s; i < candidates.length;i++) {
                if ((i > s) && (candidates[i] == candidates[i-1])) continue;
                if ( (candidates[i] <= target)  )  {
                    T.add(candidates[i]);
                    backTrack(candidates,L,T,target-candidates[i],i+1);
                    T.remove(T.size()-1);
                }

            }

        }

    }

    public static void main(String[] args) {
        //int[] A = {2,3,7,2};
        int[] A = {10,1,2,7,6,1,5};
        int[] A1 = {2,5,2,1,2};
        CombinationSumTwo comb = new CombinationSumTwo();
        List<List<Integer>> LL = comb.combinationSum2(A,8);
        List<List<Integer>> LL1 = comb.combinationSum2(A1,5);
        System.out.print("Size is "+LL.size());
        System.out.print("Size is "+LL1.size());
    }

}
