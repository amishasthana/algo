import java.util.Arrays;

public class Rotate {

    /*
   1,2,3,4 --> 2
   1,2,3,4,-MIN_INT
   */
    public void rotate(int[] nums, int k) {
        if ((nums == null) || (nums.length <= 1) || (k == 0)) return;
        int[] kTem = new int[k];
        for(int i = (nums.length-k),j= 0;i < (nums.length);i++,j++) {
            kTem[j] = nums[i];
        }
        for(int i = nums.length-1;i>=k;i--) {
            nums[i] = nums[i-k];
        }
        for(int j = 0; j<kTem.length;j++) {
            nums[j] = kTem[j];
        }
        System.out.println(Arrays.toString(nums));
    }


    public static void main(String[] args) {
        Rotate rot = new Rotate();
        int[] A = {1,2,3,4,5,6,7};
        rot.rotate(A,3);
    }
}
