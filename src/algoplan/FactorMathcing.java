package algoplan;
/*
254. Factor Combinations
Medium

Numbers can be regarded as the product of their factors.

    For example, 8 = 2 x 2 x 2 = 2 x 4.

Given an integer n, return all possible combinations of its factors. You may return the answer in any order.

Note that the factors should be in the range [2, n - 1].



Example 1:

Input: n = 1
Output: []

Example 2:

Input: n = 12
Output: [[2,6],[3,4],[2,2,3]]

Example 3:

Input: n = 37
Output: []



Constraints:

    1 <= n <= 107


 */
import java.util.*;
public class FactorMathcing {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        factor(ans,new ArrayList<>(),n,2);
        return ans;
    }

    // why will all be unique.
    // an intutive way to think --> each step you send start which is higher and num/start is lower.
    // also n < sqrt ensure it does not go over
    private void factor(List<List<Integer>> ans,List<Integer> cList,int num,int start) {
        if (num <= 1) {
            if (cList.size() > 1) {
                ans.add(new ArrayList<>(cList));// clone and added.
            }
            return;
        }
        int SQRT = (int)Math.sqrt(num);
        //int SQRT = num;
        //before sqrt all factor will be calculated. Remember with 2 the other half is higher. 2 side can reach till sqrt
        while(start <= SQRT) {
            if (num%start == 0) {
                cList.add(start);
                factor(ans,cList,num/start,start);
                cList.remove(cList.size()-1);
            }
            start++;
        }

        cList.add(num);
        factor(ans,cList,num/num,num);
        cList.remove(cList.size()-1);
    }

    public static void main(String[] args) {
        FactorMathcing fm = new FactorMathcing();
        List<List<Integer>> ans = fm.getFactors(12);
        for(List<Integer> LL : ans) {
            System.out.println(LL.toString());
        }
    }

}
