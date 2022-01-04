package backtrack;

import java.util.ArrayList;
import java.util.List;

/*
39. Combination Sum
Medium

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]


 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> L = new ArrayList<>();
        backTrack(candidates,L,new ArrayList<>(),target,0);
        return L;

    }

    private void backTrack(int[] candidates,List<List<Integer>> L, List<Integer> T,int target,int s) {
        if ( target == 0) {
            L.add(new ArrayList<>(T));
            return;
        } else {
            for(int i = s; i < candidates.length;i++) {
                if ( candidates[i] <= target) {
                    T.add(candidates[i]);
                    backTrack(candidates,L,T,target-candidates[i],i);
                    T.remove(T.size()-1);
                }

            }

        }

    }

    public static void main(String[] args) {
        //int[] A = {2,3,7,2};
        int[] A = {2,3,5};
        int[] A1 = {2,3,6,7};
        CombinationSum comb = new CombinationSum();
        List<List<Integer>> LL = comb.combinationSum(A,8);
        List<List<Integer>> LL1 = comb.combinationSum(A1,7);
        System.out.print("Size is "+LL.size());
        System.out.print("Size is "+LL1.size());
    }


}
