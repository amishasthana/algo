package backtrack;

import java.util.ArrayList;
import java.util.List;

/*
46. Permutations
Medium

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]


 */
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> R = new ArrayList<>();
        backTrack(nums,R,new ArrayList<>(),0,new boolean[nums.length]);
        return R;

    }

    private void backTrack(int[] nums,List<List<Integer>> R,List<Integer> tList,int s,boolean[] U) {
        if ( tList.size() == nums.length) {
            R.add(new ArrayList<>(tList) );
            return;
        } else {
            for(int i = 0; i < nums.length;i++) {
                if ( U[i] ) continue;
                tList.add(nums[i]);
                U[i] = true;
                backTrack(nums,R,tList,i+1,U);
                tList.remove(tList.size()-1);
                U[i] = false;
            }
        }
    }//End of method.

    public static void main(String[] args) {
        int[] A = {2,3,7};
        Permutation perm = new Permutation();
        List<List<Integer>> LL = perm.permute(A);
        System.out.print("Size is "+LL.size());
    }

}
