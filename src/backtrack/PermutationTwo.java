package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
47. Permutations II
Medium

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]


 */
public class PermutationTwo {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> R = new ArrayList<>();
        Arrays.sort(nums);
        backTrack(nums,R,new ArrayList<>(),new boolean[nums.length]);
        return R;

    }

    private void backTrack(int[] nums,List<List<Integer>> R,List<Integer> tList,boolean[] U) {
        if ( tList.size() == nums.length) {
            R.add(new ArrayList<>(tList) );
            return;
        } else {
            for(int i = 0; i < nums.length;i++) {
                if ( U[i]  || (i >0 && (nums[i] == nums[i-1]) && (!U[i-1]))) continue;
                tList.add(nums[i]);
                U[i] = true;
                backTrack(nums,R,tList,U);
                tList.remove(tList.size()-1);
                U[i] = false;
            }
        }
    }//End of method.

    public static void main(String[] args) {
        //int[] A = {2,3,7,2};
        int[] A = {2,2,3};
        PermutationTwo perm = new PermutationTwo();
        List<List<Integer>> LL = perm.permute(A);
        System.out.print("Size is "+LL.size());
    }

}
