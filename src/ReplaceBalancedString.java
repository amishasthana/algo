import java.util.HashMap;
import java.util.Map;

/*
5232. Replace the Substring for Balanced String

    User Accepted: 622
    User Tried: 1843
    Total Accepted: 632
    Total Submissions: 3601
    Difficulty: Medium

You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.

A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.

Return the minimum length of the substring that can be replaced with any
other string of the same length to make the original string s balanced.

Return 0 if the string is already balanced.



Example 1:

Input: s = "QWER"
Output: 0
Explanation: s is already balanced.

Example 2:

Input: s = "QQWE"
Output: 1
Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.

Example 3:

Input: s = "QQQW"
Output: 2
Explanation: We can replace the first "QQ" to "ER".

Example 4:

Input: s = "QQQQ"
Output: 3
Explanation: We can replace the last 3 'Q' to make s = "QWER".



Constraints:

    1 <= s.length <= 10^5
    s.length is a multiple of 4
    s contains only 'Q', 'W', 'E' and 'R'.

Java

Basically find the number say :
Q : 10
W : 6
E : 7
R : : 9

Extra
Q : 2
R : 1

So find smallest substring with 2 Q and 1 R.
 Start from 0, say at i. you have 2Q and 1 R. So max till now is x = i -0
 Now move from 0, to next vital one, and see how much you need to move. that way you will find the least.



*/
public class ReplaceBalancedString {
    int optN = 0;//Number of each char.
    char[] strArray = null;//The actual string array.
    char[] constArray = {'Q','W','E','R'};
    Map<Character,Integer> mapOfIndex = new HashMap<>();//Just the map of index.
    int[] existingNumber = new int[4];//Current counts. Will not change.
    int minLength = 0;
    boolean[] haveExtra = new boolean[4];// Will not change
    /*
        s not null, length multiple of 4 and contains only 4 char.
     */
    class LengthCal {// At most 3, so one atleast will be turned off.

        int[] findNum = new int[4];

        public LengthCal() {
            for(int i = 0; i < constArray.length;i++) {
                findNum[i] = existingNumber[i]-optN;// If positive then we are tracking to replace. If zero or negative ignore it.
                if (findNum[i] > 0) {
                    haveExtra[i] = true;
                }
            }
        }

        public boolean isMatching() {
            for(int j = 0;j < 4;j++) {
                if (!haveExtra[j]) continue;//Ignore these
                if (findNum[j] > 0) return false;
            }
            return true;
        }


        /*
            isAdd : True you ate trying to add the char. Otherwise trying to remove.
            boolean if updated.
         */
        public boolean update(char c,boolean isAdd) {
            int index = mapOfIndex.get(c);
            if (!haveExtra[index]) return false;//Ignoring character which we do not want.
            if(!isAdd) {//This will come only when its a valid char.
                findNum[index] += 1;//
            } else {
                findNum[index] -= 1;// So if you get more you go to negative.
            }
            return true;
        }

    }

    private void populateExisting(char[] constArray){
        for(char c: strArray) {
            if (c == constArray[0]) {
                existingNumber[0]++;
            } else if(c == constArray[1]) {
                existingNumber[1]++;
            } else if(c == constArray[2]) {
                existingNumber[2]++;
            } else {
                existingNumber[3]++;
            }
        }
    }

    private void init(String s) {
        strArray = s.toCharArray();
        optN = s.length()/4;
        minLength = s.length();
        int index = 0;
        for(char c : constArray) {
            mapOfIndex.put(c,index++);
        }
        populateExisting(constArray);
    }

    public int lengthOfSub(String s) {
        init(s);
        LengthCal lc = new LengthCal();
        int fIndex = 0; int sIndex = 0;
        /*
        This is the main loop.
        Two pointers. Both from start.
         We initially move the sIndex till we have found one sol. We compare the size of sol (sIndex - fIndex)+1 to min so far.
         After that we move fIndex till it reach next char which we are tracking. We modify the number to be found and start incrementing sIndex again.
         */
        if (lc.isMatching()) {
            return 0;
        }
        fIndex = findNext(fIndex);
        lc.update(strArray[fIndex],true);
        if (lc.isMatching()) {//the first one
            return 1;
        }
        sIndex = fIndex+1;
        while ((fIndex < s.length()) && (fIndex <= sIndex) && (sIndex < s.length())) {
            lc.update(strArray[sIndex], true);
            if (lc.isMatching()) {
                minLength = Math.min(minLength, (sIndex - fIndex)+1);
                if (minLength <= 4) {
                    System.out.println(" sindex = "+sIndex+" fIndex "+fIndex);
                }
                lc.update(strArray[fIndex], false);//Removing this, only place where we remove.
                fIndex = findNext(++fIndex);// No update of LC,
            }
            sIndex = findNext(++sIndex);
        }
        return minLength;
    }



    /*
         Inclusive of index.
     */
    private int findNext(int index) {
        for(int i = index;i < strArray.length;i++) {
            char currentChar = strArray[i];
            int indexOfChar = this.mapOfIndex.get(currentChar);
            if (this.haveExtra[indexOfChar]) return i;
        }
        return strArray.length;//Done
    }

    /*
    char[] constArray = {'Q','W','E','R'};
     */
    public static void main(String[] args) {
        String str1 = "QWER";
        String str2 = "QWREQQRR";
        String str3 = "QQQRREEW";
        String str4 = "WWEQERQWQWWRWWERQWEQ";
        ReplaceBalancedString rbs = new ReplaceBalancedString();
        //System.out.println(rbs.lengthOfSub(str3));
        //System.out.println(rbs.lengthOfSub(str2));
        System.out.println(rbs.lengthOfSub(str1));
    }

}
