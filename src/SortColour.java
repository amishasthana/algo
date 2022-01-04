import java.util.Arrays;

public class SortColour {
    /*
    Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Follow up:

    A rather straight forward solution is a two-pass algorithm using counting sort.
    First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
    Could you come up with a one-pass algorithm using only constant space?

// SOLUTION
Let us try to seperate using three pointers.

Z Pointer--> Will start from 0 and after this you can add Z.
1 Pointer --> Will start from 0 and will be greater then Z pointer. Again after this you can add the value.
2 Pointer --> Will start from end and on left you can add value. Will be greater then 1 pointer.

Three other values -->


     */


    /* Seriously GREAT :D
        Basically think of like this:
        We are maintaining in
        n1 : Zero pointer
        n2 : One pointer
        and n3 : Two pointer.
        We ensure that n1 >= n2 and n3 >= n2.
        So when writing zero we incerement everyting. Though we write last zer0.
        When writing one we incerement two and one. Write one last.
        Also you have to write everything again. The reason is that if one is writing, it may hve been overwriting 2 which was written previously.
        So you write that 2 in new place.
     */
    void sortColors(int nums[]) {
        if((nums == null) ||(nums.length == 0)) return;
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0)
            {
                nums[++n2]=2; nums[++n1]=1; nums[++n0] = 0;
            }
            else if (nums[i] == 1)
            {
                nums[++n2]=2; nums[++n1] = 1;
            }
            else if (nums[i] == 2)
            {
                nums[++n2] = 2;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /*
         Swap. Again three
         i, k and j.
         Basically the idea is that if you overwrite something go back.
     */

    private void swap(int[] A,int i,int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /*
        Pretty good too.
        i --> Current index, not for 0,1 or two.
        j --> Current ZERO index
        k --> Current 2 index.
     */
    void sortColorsSwap(int nums[]) {
        if((nums == null) ||(nums.length == 0)) return;
       int j = 0;int k = nums.length-1;
        for ( int i = 0; i < k;i++) {
            if(nums[i] == 2) {//Def need to move it up.
                swap(nums,i,k);
                i--;//I go back, you dont know what is there in ith pos. K is good.
                k--;
            } else if(nums[i] == 0) {
                swap(nums,i,j);
                j++;//One index increased. I is good. It can have 0, 1 or two all of them fine. Note i >= j
            }
        }
        System.out.println(Arrays.toString(nums));
    }



    public static void main(String[] args) {
        SortColour sCol = new SortColour();
        int[] b = {2,0,2,1,1,0};
        int[] a = {2,0,1};
        sCol.sortColors(a);
        sCol.sortColorsSwap(b);
        //sCol.sortColors(a);
        //sCol.sortColorsSwap(b);
    }
}
