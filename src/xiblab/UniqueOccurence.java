package xiblab;

import java.util.Arrays;
import java.util.TreeSet;
/*
Basically all small english character.
Say ccaaffddecee
--> c -> 3
    a -> 2
    f - > 2
    d -> 2
    e -> 3
    How many to remove so that the frequency of each character is unique.
    You can always make it to zero.
 */
public class UniqueOccurence {
    public int solution(String s) {
        int l = s.length();
        if (l <=1) return l;
        TreeSet<Integer> tSet = new TreeSet<>();
        for(int i = 1; i <= l;i++) tSet.add(i);
        int[] cMap = new int[26];
        for(char c : s.toCharArray()) {
            cMap[c - 'a']++;
        }
        int[] nSorted = Arrays.copyOf(cMap,cMap.length);
        Arrays.sort(nSorted);
        int ops = 0;

        for(int i = 0; i < 26;i++) {
            if (nSorted[i] >0) {
                int f = nSorted[i];
                if (tSet.contains(f)) {
                    tSet.remove(f);
                } else {
                    Integer floor = tSet.floor(f);
                    int existing = (floor == null)?0:floor;
                    ops += ( f - existing );
                    tSet.remove(existing);
                }
            }
        }

        return ops;
    }

    public static void main(String[] args) {
        UniqueOccurence uq = new UniqueOccurence();
        String s ="ccaaffddecee";
        System.out.println(uq.solution(s));
    }
}
