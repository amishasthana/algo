
    /*
    You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1

Note:
You may assume that you have an infinite number of each kind of coin.

SOl {n] = MIN ((sol(n-i)+ i)

*/
public class CoinChange {
       int[] sol = null;

        public int coinChange(int[] coins, int amount) {
                  if ((coins == null) || (coins.length == 0) ) {
                      return -1;
                  }
                  if (amount == 0) return 0;
                  int coinL = coins.length;

                  sol = new int[amount];

                  for(int i = 1;i <= amount;i++) {
                      int minCoins = Integer.MAX_VALUE;
                      for(int n = 0; n < coins.length;n++) {
                           if ((i-coins[n]) > 0){
                                  //Possible solution.
                                    int solForPrev = sol[getIndex((i-coins[n]),coinL)];
                                    if (solForPrev != Integer.MAX_VALUE) {// -1 is no solution.
                                        minCoins = Math.min(minCoins,solForPrev+1);
                                    }
                           } else if ((i-coins[n]) == 0) {
                               minCoins = 1;break;
                           }
                      }
                      sol[getIndex(i,coinL)] = minCoins;
                  }
                  int ret =  sol[getIndex(amount,coinL)];
                  if (ret == Integer.MAX_VALUE) {
                      return -1;
                  } else {
                      return ret;
                  }
        }

        private int getIndex(int i, int l) {
            return i-1;
        }

        public static void main(String[] args) {
            int[] A = {2,3,5};
            CoinChange cc = new CoinChange();
            System.out.println("Result "+cc.coinChange(A,1));
        }

}
