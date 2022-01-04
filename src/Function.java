import java.util.ArrayList;
import java.util.List;

public class Function {
    public class CustomFunction {


         public int f(int x, int y) {
             return x+y;
         }
  };

    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> retList = new ArrayList<>();
        for(int x = 1; x< z;x++) {
            for(int y = 1; y< z;x++) {
                if (customfunction.f(x,y) == z) {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(x);
                    newList.add(y);
                    retList.add(newList);
                }
            }
        }

        return retList;
    }

    public CustomFunction getCustom() {
        return new CustomFunction();
    }

    public static void main(String[] args) {
        Function fn = new Function();

        List<List<Integer>>  list = fn.findSolution(fn.getCustom(),5);
        for(List<Integer> lits : list ) {
            System.out.print(lits.size());
        }
    }
}
