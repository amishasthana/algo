import java.util.ArrayList;
import java.util.List;

/*
78. Subsets
Medium

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]


 */
public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> mList = new ArrayList<>();
        backtrack(mList,new ArrayList<>(),0,nums);
        return mList;
    }

    private void backtrack(List<List<Integer>> mList,List<Integer> cList,int s,int[] nums) {
        mList.add(new ArrayList(cList)); //clone.
        for(int i =s; i < nums.length;i++) {
            cList.add(nums[i]);//Added the current elem
            backtrack(mList,cList,i+1,nums);
            cList.remove(cList.size()-1);//Remove the current elem.
        }

    }

}
