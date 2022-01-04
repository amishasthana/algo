package algoplan;
/*
241. Different Ways to Add Parentheses
Medium

Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.



Example 1:

Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2

Example 2:

Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10



Constraints:

    1 <= expression.length <= 20
    expression consists of digits and the operator '+', '-', and '*'.
    All the integer values in the input expression are in the range [0, 99].


 */
import java.util.*;
public class PCombination {
    public List<Integer> diffWaysToCompute(String expression) {
       List<Integer> lInt  = new ArrayList<>();//getInteger(expression);
       List<Character> lChar = new ArrayList<>();
       populate(expression,lInt,lChar);
       Map<String,List<Integer> > map = new HashMap<>();//key start+end index ,Index of list.
       calculate(map,lInt,lChar,0,lInt.size());
       List<Integer> ans = new ArrayList<>((List<Integer>)map.get(0+"_"+(lInt.size())));
       return ans;
    }

    //i inclusive j exclusive
    private void calculate(Map<String,List<Integer> > map,List<Integer> lInt,List<Character> lChar,int i,int j) {
        if ( i == j) return;
        String key = i+"_"+j;
        if (map.get(key) != null) return;
        if (i == j-1) {
            map.put(key,List.of(lInt.get(i)));
            return;
        }
        List<Integer> newSet = new ArrayList<>();
        map.put(key,newSet);
        for(int ii = i+1;ii < j;ii++) {
            calculate(map,lInt,lChar,i,ii);
            String key1 = i+"_"+ii;
            List<Integer> lSet = map.get(key1);

            calculate(map,lInt,lChar,ii,j);
            String key2 = ii+"_"+j;
            List<Integer> rSet = map.get(key2);

            char sign = lChar.get(ii-1);
            for(Integer fint : lSet) {
                for(Integer sint : rSet) {
                    if (sign == '+') {
                        newSet.add(fint+sint);
                    } else if (sign == '-') {
                        newSet.add(fint-sint);
                    } else {
                        newSet.add(fint*sint);
                    }
                }
            }
        }

    }

    private void populate(String exp,List<Integer> lInt,List<Character> lChar) {
        char[] cArr = exp.toCharArray();
        int j = 0;int i = 0;
        for (; i < cArr.length; i++) {
            char c = cArr[i];
            if ((c == '-') || (c == '+') || (c == '*')) {
                lChar.add(c);
                lInt.add(Integer.valueOf(exp.substring(j, i)));
                j = i+1;
            }
        }
        lInt.add(Integer.valueOf(exp.substring(j, i)));
    }

    public static void main(String[] args) {
        PCombination pc = new PCombination();
        List<Integer> ll = pc.diffWaysToCompute("2-1-1");
        System.out.println(ll.toString());
    }
}
