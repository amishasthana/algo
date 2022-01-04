package BinarySearch;

import java.util.*;

/*
514. Freedom Trail
        Hard

        In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.

        Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.

        Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.

        At the stage of rotating the ring to spell the key character key[i]:

        You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
        If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.

        Example:


        Input: ring = "godding", key = "gd"
        Output: 4
        Explanation:
        For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
        For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
        Also, we need 1 more step for spelling.
        So the final output is 4.

        Note:

        Length of both ring and key will be in range 1 to 100.
        There are only lowercase letters in both strings and might be some duplcate characters in both strings.
        It's guaranteed that string key could always be spelled by rotating the string ring.

    The basic idea is to think of it as a graph. Think of n*n --> connections with min of either way.
    Then using the key --> navigate the graph. Start from back.
    From X char(2 instances) --> Y char(3 instances, from bottom) there are 2 mins once for each char. Store that value and procced further.
*/
public class RotatingStep {
    int l;
    private int findMinDist(int p1,int p2) {
        int d = Math.abs(p2-p1);
        return Math.min(l-d,d);
    }

    class PosMin {
        char c;
        int posInRing;
        int minD = 0;//Integer.MAX_VALUE;

        public PosMin(char c,int posInRing) {
            this.c = c; this.posInRing = posInRing;
        }
    }

    public int findRotateSteps(String ring, String key) {
        l = ring.length();
        int press = key.length();
        Map<Character,List<Integer>> map = new HashMap<>();
        char[] cR = ring.toCharArray();
        for(int i = 0; i < l ;i++) {
            List<Integer> lis = map.getOrDefault(cR[i],new LinkedList<>());
            lis.add(i);
            map.put(cR[i],lis);
        }// end of for --> for creating map.
        List<PosMin> Q1 = new LinkedList<>();
        PosMin sol = null;

        if ( ring.charAt(0) != key.charAt(0) ) {
            key = ring.charAt(0)+key;
        }
        int kL = key.length();
        char prevChar = ' ';
        for(int ri = kL-1;ri >= 0; ri--) {
            char c = key.charAt(ri);
            if (prevChar == c) continue;
            prevChar = c;
            List<Integer> ll = map.get(c);//From ring
            List<PosMin> Q2 = new LinkedList<>();
            for(int i : ll) {
                PosMin pMin = new PosMin(c,i);
                if (i == 0) sol = pMin;
                Q2.add(pMin);
                for(PosMin pp : Q1) {
                    pMin.minD = Math.min(((pMin.minD == 0)?Integer.MAX_VALUE:pMin.minD),findMinDist(i,pp.posInRing)+pp.minD);
                }// Inner for
            }//Outer for
            Q1 = Q2;
        }// End of for
        int min = Integer.MAX_VALUE;

        return sol.minD+press;
    }

    public static void main(String[] args) {
        RotatingStep rs = new RotatingStep();
        //"ababcab"
        //"acbaacba"
        System.out.println(rs.findRotateSteps("ababcab","acbaacba"));
    }

}
