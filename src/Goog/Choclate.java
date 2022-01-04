package Goog;

public class Choclate {
    /*
Let me divide in K entries.
Basically i will get minimum.
I have to maximize my gain.
Min break is min element.
Max is sum of array.
Basically all will be in between.
*/
    public int maximizeSweetness1(int[] sweetness, int K) {
        if ( ( sweetness == null) || (sweetness.length == 0) || ( K > sweetness.length) ) return -1;
        int n = sweetness.length; int sum = 0; int min = Integer.MAX_VALUE;
        for(int ii : sweetness) {
            sum += ii;
            min = Math.min(min,ii);
        }

        int lo = min; int hi = sum;
        while ( lo < hi) {
            int mid = (lo+hi+1)/2;
            int cSum = 0; int part = 0;
            for(int ii = 0; ii < n ; ii++) {
                cSum += sweetness[ii];
                if (cSum >= mid) {
                    //System.out.println(" Index "+ii+" "+sweetness[ii]);
                    part++;
                    cSum = 0;
                }
            }//end of for

            if ( part <= K) {
                hi = mid-1;
            } else {
                lo = mid;
            }
        }//end of while
        return  lo;

    }

    public int maximizeSweetness2(int[] A, int K) {
        int left = 1, right = (int)1e9 / (K + 1);
        while (left < right) {
            int mid = (left + right + 1) / 2;
            int cur = 0, cuts = 0;
            for (int a : A) {
                if ((cur += a) >= mid) {
                    cur = 0;
                    if (++cuts > K) break;
                }
            }
            if (cuts > K)
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }

    public int maximizeSweetness(int[] sweetness, int K) {
        int l = sweetness.length;
        int min = Integer.MAX_VALUE; int max = 0;
        for(int i = 0 ; i < l ; i++) {
            max += sweetness[i];
            min = Math.min(min,sweetness[i]);
        }
        int[] s = sweetness;
        int ret = 0;
        while (min <= max) {
            int mid  = min+ (max - min)/2;
            int sum = 0;
            int p = 0;
            for(int i = 0; i < l; i++ ) {
                sum += s[i];
                if (sum >= mid) {
                    p++;
                    sum = 0;
                }
            }
            if (( p > K) || ((p == K) && (sum >= mid)) ) {
                ret = mid;
                min = mid+1;
            } else {
                max = mid-1;
            }
        }
        return ret;
    }


    public static void main(String[] args) {
        Choclate cc = new Choclate();
        int[] A = {1,2,3,4,5,6,7,8,9};
        System.out.println(cc.maximizeSweetness(A,5));
    }

}
