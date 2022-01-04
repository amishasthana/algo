/*
Longest subarry less then K.


 */

public class LongestSubArrayLessThenK {
    /*
        n2 find all such sums. However this is
        However this does not give proper result when we dont have constraint that all intermediate subarray follow the rule.
        For example :
        Gives 7 , though it can be nine.
     */
    public int bruteForce(int[] iA, int k) {
        int l = 0;
        for(int i = 0; i < iA.length;i++) {
            int sum = 0;
            for(int j = i; j < iA.length;j++) {
                sum += iA[j];
                if (sum > k) {
                    break;
                }
                if (l < (j-i)+1) {
                    l = j-i+1;
                }
            }
        }
        return l;
    }

    public int bruteForceAllArray(int[] iA, int k) {
        int l = 0;
        for(int i = 0; i < iA.length;i++) {
            if ((iA.length - i) < l) return l;
            int sum = 0;
            for(int j = i; j < iA.length;j++) {
                sum += iA[j];
                //System.out.println(sum + " anc "+i+" "+j);
                if (sum < k) {
                    //System.out.println(sum + " and "+i+" "+j);
                    //System.out.println(sum + " and "+i+" "+j);
                    int cLength = (j - i)+1;
                    if (l < cLength) {
                        l = cLength;
                    }
                }
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] iA = {1,2,3,-3,4,2,-3,-3,1,10};
        LongestSubArrayLessThenK lgk = new LongestSubArrayLessThenK();
        System.out.println(lgk.bruteForce(iA,6));
        System.out.println(lgk.bruteForceAllArray(iA,6));
    }

}
