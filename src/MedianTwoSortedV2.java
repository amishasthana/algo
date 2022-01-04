

public class MedianTwoSortedV2 {

    public double medianTwoSortedArray(int[] a,int[] b) {
        int l1 = ((a == null) || (a.length == 0))?0:a.length;
        int l2 = ((b == null) || (b.length == 0))?0:b.length;

        int size = (l1+l2 + 1)/2;
        if (size == 0) {
            throw new IllegalArgumentException("Not valid array");
        }
        int fIndex = 0;
        int sIndex = 0;
        int m1 = Integer.MIN_VALUE; int m2 = Integer.MIN_VALUE;
        for(int i =0; i<= size;i++) {
            int fElement = Integer.MIN_VALUE;
            int sElement = Integer.MIN_VALUE;
            if (fIndex < l1) {
                fElement = a[fIndex];
            }
            if (sIndex < l2) {
                sElement = b[sIndex];
            }
            if(fElement <= sElement) {
                fIndex++;
                if(m1 == Integer.MIN_VALUE) {
                    m1 = fElement;
                } else {
                    if(m2 != Integer.MIN_VALUE) {
                        m1 = m2;
                    }
                    m2 = fElement;
                }

            } else {
                sIndex++;
                if(m1 == Integer.MIN_VALUE) {
                    m1 = sElement;
                } else {
                    if(m2 != Integer.MIN_VALUE) {
                        m1 = m2;
                    }
                    m2 = sElement;
                }

            }

        }//End of for.
        if (((l1+l2 + 1) % 2 ) == 0) {
            return m1;
        } else {
            return (m1+m2)/2.0;
        }
    }//End of method

    public static void main(String[] args) {
        int[] a1 = {1,3,7,8,11,14};
        int[] a2 = {2,7,9,10,11,13};
       // int[] a1 = {1,3};
       // int[] a2 = {2,7,7};
        MedianTwoSortedV2 mts = new MedianTwoSortedV2();
        System.out.println(mts.medianTwoSortedArray(a1,a2));
    }
}
