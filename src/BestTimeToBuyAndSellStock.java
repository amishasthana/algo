/*
Say you have an array for which the ith element is the price of a given stock on day i.

        Design an algorithm to find the maximum profit. You may complete at most two transactions.

        Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

        First try only one txn.
        maxEverLowestIndex, maxEverHighestIndex, maxDeltaEver
        lowestPrice , lowIndex
        maxPrice, maxIndex
        currentDelta
        a) Start some base price.
           b) If currPrice is lower discard this and use that on that is change lowIndex
           c) if curre index price greater then low index price. Calculate the currentDelta. if current delta greater then currentDelta so far this is the currentDelta.
           d) Check against maxPrice.

       With 2 it becomes difficult.
       Lets take an example.
       1   3    2    12
       1-12 --> 11
       1-3 --> 2
       2-12 --> 10

     What if I grow the array.
     2,96,18,1,21,80,93,2,42,10,25,22,64,35,18,50,3,10,98,19,243
                                                        H  H

     Worst case :
    For each day, assume yu bought on that day, what is the max you could

*/
public class BestTimeToBuyAndSellStock {

    public int maxInSingleTrade(int[] sPrice) {
        if((sPrice == null) && (sPrice.length <= 1)) return 0;
        int maxProfit = 0;
        int lIndex, hIndex = 0;
        int maxLIndex= 0;
        int maxhIndex = 0;

        lIndex = 0;
        for(int i = 1; i < sPrice.length;i++) {
            hIndex = i;
            if (sPrice[hIndex] < sPrice[lIndex]) {//Current price lower then what was earlier lowest price
                lIndex = hIndex;
            } else {
                if ((sPrice[hIndex] - sPrice[lIndex]) > maxProfit) {
                    maxProfit = sPrice[hIndex] - sPrice[lIndex];
                    maxLIndex = lIndex;
                    maxhIndex = hIndex;
                }
            }
        }
        System.out.println("The maximum profit is "+maxProfit);
        System.out.println("The maximum profit is between  "+maxLIndex+" and "+maxhIndex);
        return maxProfit;
    }
/*
Beautiful : Basically at each element, its tracking four vars. What would have happened if I have done 2 trades, till this time.
Again its maybe two trade(it can be one only.)
And basically max is tracked, so you can print it out. It does not track when it start/end etc.
var maxProfit = function(prices) {
  let oneBuyOneSell = 0;
  let twoBuyTwoSell = 0;
  let oneBuy = Number.POSITIVE_INFINITY
  let twoBuy = Number.POSITIVE_INFINITY;

  for(let i = 0; i < prices.length; i++) {
    const p = prices[i];
    oneBuy = Math.min(oneBuy, p);
    oneBuyOneSell = Math.max(oneBuyOneSell, p - oneBuy);
    twoBuy = Math.min(twoBuy, p - oneBuyOneSell);
    twoBuyTwoSell = Math.max(twoBuyTwoSell, p - twoBuy);
  }

  return twoBuyTwoSell;
};

public int

 */

    public int maxProfitWithTwoTxn(int[] prices) {

        int firstBuy = Integer.MAX_VALUE;
        int secondBuy = Integer.MAX_VALUE;
        int firstSell = 0;
        int secondSell = 0;
        if((prices == null) && (prices.length <= 1)) return 0;
        for(int i : prices) {
            firstBuy = Math.min(firstBuy,i);
            firstSell = Math.max(firstSell,i-firstBuy);
            secondBuy = Math.min(secondBuy,i-firstSell);
            secondSell = Math.max(secondSell,i-secondBuy);
        }
        return secondSell;
    }

    class Txn {
        int sell;
        int buy;
    }



    /*
    Sell if next day price is lower then today.
    */
    public int maxProfitWithAnyNumberOftxn(int[] prices) {
        if ((prices == null) || (prices.length <= 1)) return 0;
        int buyPrice = prices[0];
        int totalProfit = 0;

        for(int i : prices) {
            if (buyPrice > i) {//Bought today
                buyPrice = i;
            }
            totalProfit += (i-buyPrice);
            buyPrice = i;
        }
        return totalProfit;
    }
    public int maxProfitWithKTxn(int[] prices,int k) {
        if((prices == null) && (prices.length <= 1)) return 0;
        Txn[] txnArray = new Txn[k];
        for(int i = 0; i < k; i++) {
            txnArray[i] = new Txn();
            txnArray[i].buy = Integer.MAX_VALUE;
            txnArray[i].sell = Integer.MIN_VALUE;
        }

        for(int p : prices) {
            for(int i = 0; i < k; i++) {
                txnArray[i].buy = Math.min(txnArray[i].buy,(i ==0)?p:p-txnArray[i-1].sell);
                txnArray[i].sell = Math.max(txnArray[i].sell,p-txnArray[i].buy);
            }
        }
        return txnArray[k-1].sell;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock btbas = new BestTimeToBuyAndSellStock();
        //int[] A = {2,96,18,1,21,80,93,2,42,10,25,22,64,35,18,50,3,10,98,19,243};
        //int[] A = {7,1,5,3,6,4};
        //int[] A = {2,4,1};
        int[] A = {2,4,1};
        //System.out.println(btbas.maxProfitWithAnyNumberOftxn(A));
        System.out.println(btbas.maxProfitWithKTxn(A,2));
    }

}
