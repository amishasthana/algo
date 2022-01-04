import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
90. Subsets II
Medium

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]


 */
public class SubSet2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> mList = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(mList,new ArrayList<>(),0,nums);
        return mList;
    }

    private void backtrack(List<List<Integer>> mList,List<Integer> cList,int s,int[] nums) {
        mList.add(new ArrayList(cList)); //clone.
        for(int i =s; i < nums.length;i++) {
            if (i > s) {
                if (nums[i] == nums[i-1])  continue;
            }
            cList.add(nums[i]);//Added the current elem
            backtrack(mList,cList,i+1,nums);
            cList.remove(cList.size()-1);//Remove the current elem.
        }
    }
}
