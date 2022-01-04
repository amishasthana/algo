import java.util.TreeSet;

/*
475. Heaters
Easy

Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:

    Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
    Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
    As long as a house is in the heaters' warm radius range, it can be warmed.
    All the heaters follow your radius standard and the warm radius will the same.


 */
public class Heater {
    public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> hSet = new TreeSet<>();
        for(int h : heaters) {
            hSet.add(h); // will be sorted based on position.
        }
        int minRadius = Integer.MIN_VALUE;
        for(int h : houses) {
            int cRadius = Math.max( Math.abs(hSet.ceiling(h) - h), Math.abs(h-hSet.floor(h)) );
            if (cRadius > minRadius) minRadius = cRadius;
        }
        return minRadius;
    }

}
