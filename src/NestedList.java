/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8
Explanation: Four 1's at depth 1, one 2 at depth 2.

Example 2:

Input: [1,[4,[6]]]
Output: 17
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.



* // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
import java.util.*;
public class NestedList {
    public interface NestedInteger {

          public boolean isInteger();  public Integer getInteger();
 public void setInteger(int value);
 public void add(NestedInteger ni);
    public List<NestedInteger> getList();
    }

    int maxDepth = 0;

    Map<Integer,Integer> mapOfSum = new HashMap<>();
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if ((nestedList == null) || (nestedList.size() == 0)) {
            return 0;
        }
        int sum = 0;
        populateMap(nestedList,0);
        for (Map.Entry<Integer, Integer> entry : mapOfSum.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            sum += ((maxDepth - key+1)*value);

        }
        return sum;
    }

    private void populateMap(List<NestedInteger> nestedList,int cDepth) {
            if (cDepth > maxDepth) {
                maxDepth = cDepth;
            }
            for(NestedInteger cElem : nestedList) {
                if (cElem.isInteger()) {
                    Integer sumAtLevel = mapOfSum.get(cDepth);
                    if ( sumAtLevel == null) {
                        sumAtLevel = cElem.getInteger();
                        mapOfSum.put(cDepth,sumAtLevel);
                        continue;
                    }
                } else {//Has to be list
                    populateMap(cElem.getList(),cDepth+1);
                }
            }
    }


}
