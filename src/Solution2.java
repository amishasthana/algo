import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
here are n people whose IDs go from 0 to n - 1 and each person belongs exactly to one group. Given the array groupSizes of length n telling the group size each person belongs to, return the groups there are and the people's IDs each group includes.

You can return any solution in any order and the same applies for IDs. Also, it is guaranteed that there exists at least one solution.



Example 1:

Input: groupSizes = [3,3,3,3,3,1,3]
Output: [[5],[0,1,2],[3,4,6]]
Explanation:
Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].

Example 2:

Input: groupSizes = [2,1,3,3,3,2]
Output: [[1],[0,5],[2,3,4]]

 */
public class Solution2 {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> retList = new ArrayList<>();
        int index = 0;
        Map<Integer,List<Integer>> mapOfPendingList = new HashMap<>();
        for(int i : groupSizes) {
            List<Integer> existingList = mapOfPendingList.get(i);
            if (existingList == null) {
                existingList = new ArrayList<>();
                mapOfPendingList.put(i,existingList);
                retList.add(existingList);
            }
            existingList.add(index);
            if (existingList.size() == i) {//remove from map.
                mapOfPendingList.remove(i);
            }
            index++;
        }

        return retList;
    }

    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        int[] groupSizes = {2,1,3,3,3,2};
        System.out.print(sol.groupThePeople(groupSizes));
    }
}
