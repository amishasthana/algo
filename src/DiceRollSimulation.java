/*
Dice Roll Simulation
1223. Dice Roll Simulation
Medium

A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.

Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.

Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: n = 2, rollMax = [1,1,2,2,2,3]
Output: 34
Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.

Example 2:

Input: n = 2, rollMax = [1,1,1,1,1,1]
Output: 30

Example 3:

Input: n = 3, rollMax = [1,1,1,2,2,3]
Output: 181



Constraints:

    1 <= n <= 5000
    rollMax.length == 6
    1 <= rollMax[i] <= 15
       DP[i][7] --> At end of round i what is the total combination.
       DP[i][f]  ---> What is the total if in this round I have F.
         Generally [i][f] --> DP[i-1][7]
         However if it reaches the limit then DP[i-1][7] - DP[i-1][f] .
         Basically saying subtract all cases of f occuring in previous round.

 */
public class DiceRollSimulation {
    public int dieSimulator(int n, int[] rollMax) {
        long M = 1000000007;
        long[][] DP = new long[n+1][7];// Number of round. Store the sum in 7th one.
        DP[0][6] = 1;
        for(int i = 1; i <= n ; i++) {
            for(int f = 0; f < 6; f++) {
                for(int r = 1; r <= rollMax[f] && (i-r) >= 0;r++) { //For each there is bifurcation :(
                    DP[i][f] = ( DP[i][f]  + DP[i - r][6] - DP[i - r][f])%M+M;
                }
                DP[i][6] = (DP[i][6] + DP[i][f])%M;
            }//end of face.
        }// End of round
        return (int)DP[n][6];
    }

    public static void main(String[] args) {
        DiceRollSimulation dr = new DiceRollSimulation();
        int[] A = {1,1,1,2,2,3};
        System.out.println(dr.dieSimulator(3,A));
    }


}
