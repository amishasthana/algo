import java.util.*;

public class PermutationWithDuplicates {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }


    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            tempList.forEach(x->System.out.print(x+" "));
            System.out.println();
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i - 1])) {
                    System.out.println("for "+i+" "+((i >0)?(used[i]+" "+used[i-1]):"NILL"));

                    continue;
                }
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PermutationWithDuplicates pw = new PermutationWithDuplicates();
        int[] n = {1,2,2};
        List<List<Integer>> ll = pw.permuteUnique(n);
        ll.forEach(l->l.forEach(x->System.out.print(x+" ")));
    }
}
