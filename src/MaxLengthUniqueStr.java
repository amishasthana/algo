import java.util.*;
/*


Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.



Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.

Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".

Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26



Constraints:

    1 <= arr.length <= 16
    1 <= arr[i].length <= 26
    arr[i] contains only lower case English letters.


 */

public class MaxLengthUniqueStr {

    public int maxLength(List<String> arr) {
        int i = 0;
        Map<String,Set<Integer>> mapKeys = new HashMap<>();
        Set<Integer> emptySet = new HashSet<>();
        mapKeys.put("",emptySet);
        int maxLength = 0;
        for(String s : arr) {
            if(isValidString(s)) {
                /*
                if (i == 0) {
                    Set<Integer> newSet = new HashSet<>();
                    newSet.add(i);
                    mapKeys.put(s, newSet);
                    maxLength = s.length();

                } else { */
                    List<String> keysToJoin = new LinkedList<>();
                    for (String entSet : mapKeys.keySet()) {
                        boolean canJoin = true;
                        for (char c : s.toCharArray()) {//Can or can not join
                            if (entSet.contains(c + "")) {
                                canJoin = false;
                                break;
                            }
                        }//end of can or can not join
                        if (canJoin) {
                            keysToJoin.add(entSet);
                        }
                    }// End of going through existing map.
                    for (String key : keysToJoin) {
                        String nKey = key + s;
                        Set<Integer> setOfInt = mapKeys.get(key);
                        setOfInt.add(i);
                        mapKeys.put(nKey, setOfInt);
                        if (nKey.length() > maxLength) {
                            maxLength = nKey.length();
                        }
                    }
                //}
            }
            i++;
        }// End of thing
        return maxLength;

    }

    private boolean isValidString(String s) {
        int[] iA = new int[26];
        for(char c : s.toCharArray()) {
            int j = c - 'a';
            if (iA[j] != 0) {
                return false;
            }
            iA[j] = 1;
        }
        return true;
    }

    public static void main(String[] args) {
        MaxLengthUniqueStr ml = new MaxLengthUniqueStr();
        String[] nStr1 = {"un","iq","ue"};
        String[] nStr2 = {"cha","r","act","ers"};
        String[] nStr3 = {"abcdefghijklmnopqrstuvwxyz"};
        String[] nStr4 = {"yy","bkhwmpbiisbldzknpm"};
        String[] nStr5 = {"xdvqiefpcs","vgnacw","raobcnwgufztdykmsvlj"};
        System.out.println(ml.maxLength(Arrays.asList(nStr1)));
        System.out.println(ml.maxLength(Arrays.asList(nStr2)));
        System.out.println(ml.maxLength(Arrays.asList(nStr3)));
        System.out.println(ml.maxLength(Arrays.asList(nStr4)));
        System.out.println(ml.maxLength(Arrays.asList(nStr5)));
    }
}
