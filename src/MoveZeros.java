import java.util.Arrays;

/*
283. Move Zeroes
Easy

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:

    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.

 */
public class MoveZeros {
    public void moveZeroes(int[] nums) {
        if ((nums == null) || (nums.length <= 1)) return;
        int nZero = 0;
        int zIndex = -1;
        for(int i = 0;i < nums.length;i++) {
            if ( nums[i] == 0) {
                if (nZero == 0) zIndex = i;
                nZero++;
            } else if (nZero > 0)  {
                nums[i-nZero] = nums[i];
                nums[i] = 0;
                zIndex++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        int[] iA = {1,0,3,0,7};
        MoveZeros mZ = new MoveZeros();
        mZ.moveZeroes(iA);

    }
}
