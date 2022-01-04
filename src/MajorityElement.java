public class MajorityElement {
    /*
        Given an array figure out majority element.
        X is majority element in array of length l, if freq(l) >= l/2 + 1;
        If no majority element then -1

        Solution :
        The basic ide is that we start with one side of array. Any occurence +1, any occurence of other element = -1.
        If total = 0 then no majority so far.
        Then we pick the next element. The basic idea is that any majority element X will offset all othere element and still
        come out with "1"
        However just because we got some such element its not necessary that its majority. A case in point is :
        a,b,c : c will come out with 1.
        But there is no majority.
     */

    public int getMajority(int[] iA) {
         if ((iA == null) || (iA.length == 0)) {
             return -1;
         }
         int mElementSoFar = -1;
         int freq = 0;
         for(int i : iA) {
             if (freq == 0) {
                 mElementSoFar = i;
                 freq++;
             } else if (mElementSoFar == i) {
                 freq++;
             } else {
                 freq--;
             }
         }
         if (freq != 0) {
             if (validateMajority(mElementSoFar,iA)) {
                 return mElementSoFar;
             }
         }
         return -1;
    }

    private boolean validateMajority(int mElement,int[] iA) {
        int freq = iA.length/2 + 1;
        for(int i : iA) {
            if (i == mElement) {
                freq--;
            }
            if (freq <= 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] iArr = new int[]{3,2,2,1,1,1};
        MajorityElement maj = new MajorityElement();
        System.out.println("The majority element is "+(maj.getMajority(iArr)));
    }


}
