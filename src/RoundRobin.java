import java.util.HashSet;
import java.util.Set;

public class RoundRobin {

    /*
        Could not figure it out myself.
        Anyway the formula is :
        1) Get the input. If even go to step 3.
        2) Otherwise add a dummy and make it even.
        3) Divide into two. Think of two sub arrays.
        4) Fix one element, say the first element of first array.
        5) Each round print a[i],b[i](Ignore the dummy pair)
        6) Rotate clockwise each round (beside the fixed element.)
        7) End when the element comes back to original place.

        An example :
        6 element.

        1 , 2, 3
        4,  5, 6
        So R1 --> 1,4 2,5  and 3,6
        Rotate
        1  4  2
        5  6  3
        .....
     */

      public Set<Set<String>> getRounds(int n) {
          if (n <= 1) {
              throw new IllegalArgumentException("Atleast should have two element");
          }
          Set<Set<String>> aRound = new HashSet<>();
          Set<String> valSet = new HashSet<>(); //Just for validation.
          boolean dummyAdded = false;
          if (n%2 != 0) {
              dummyAdded = true;
               n = n+1;
          }
          int[] fA = new int[n/2];
          int[] fB = new int[n/2];
          for(int i = 0; i < n/2;i++) {
              fA[i] = i;
              fB[i] = i+n/2;
          }
          String byeString = null;
          for (int j = 0; j< n-1;j++) {//For each round
              System.out.println("For round "+j+1);
              Set<String> cRoundSet = new HashSet<>();
              for(int k = 0; k < fA.length;k++) {
                  int bye = -1;
                  boolean dummyThisIter = false;
                  if (dummyAdded) {
                       if (fA[k] == (n-1))  {
                           bye = fB[k];
                           dummyThisIter = true;
                       } else if (fB[k] == (n-1)) {
                           bye = fA[k];
                           dummyThisIter = true;
                       }
                  }
                  if (!dummyThisIter) {
                      String pair = (fA[k]+1) + "," + (fB[k]+1);
                      System.out.print(pair);
                      System.out.print(" ");
                      cRoundSet.add(pair);
                      valSet.add(pair);
                  } else {
                      byeString = (bye+1)+" has bye ";
                      cRoundSet.add(byeString);
                      valSet.add(byeString);

                  }

              }
              aRound.add(cRoundSet);
              System.out.print(byeString);
              rotate(fA,fB);
              //Right now not putting any special condition to check end condition. Assuming n-1 loop make sure of it.
              System.out.println("");
          }//End of round
          if (valSet.size() == ((n-1)*n/2)) {
              System.out.println("Validation valid and total distinct element "+valSet.size());
          } else {
              System.out.println("Validation failed and total distinct element "+valSet.size());
          }
          return aRound;
      }

      /*
      Assume a.length = b.length
      Also a[0] is constant.
       */
      private void rotate(int[] a,int[] b) {
          int firstOfB = b[0];
          int l = b.length;
          //Rotate b.
          for(int i = 0; i < (l-1); i++) {
              b[i] = b[i+1];
          }
          b[l-1] = a[l-1];
          //Rotate a.
          for(int i = l-1; i > 1; i--) {
              a[i] = a[i-1];
          }
          a[1] = firstOfB;
      }

      public static void main(String[] args) {
          RoundRobin rr = new RoundRobin();
          rr.getRounds(6);
          rr.getRounds(5);
      }

}
