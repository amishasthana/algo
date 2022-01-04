import java.util.*;

public class OpenTheLock {

    Map<String,Integer> vMapIncreasing = new HashMap<>();

    Set<String> dSet = new HashSet<>();
    public static final String START = "0000";

    public int openLock(String[] deadends, String target) {
        popDead(deadends);
        if ( START.equals(target) ) return 0;

        Queue<String> qInc = new LinkedList<>();
        qInc.add(START);
        vMapIncreasing.put(START,0);



        int minHopInc = -1;
        while ( !qInc.isEmpty() ) {
            String cStr1 = qInc.poll();
            int hopInc = vMapIncreasing.get(cStr1)+1;
            Set<String> childs = getChildrenIncreasing(cStr1);
            for(String c : childs) {
                if ( target.equals(c) ) {
                   return hopInc;
                }

                if (vMapIncreasing.get(c)  == null) {
                    vMapIncreasing.put(c, hopInc);
                    qInc.add(c);
                }
            }
        }// End of while



        return minHopInc;
    }//end of method

    private void popDead(String[] deadends) {
        for(String s : deadends) {
            dSet.add(s);
        }
    }

    int index = 0;
    int MAX_INDEX = 10;
    private Set<String> getChildrenIncreasing(String s) {
        Set<String> childSet = new HashSet<>();
        index++;

        if (index < MAX_INDEX) {
            System.out.println();
            System.out.print("Initial String " + s + " ");
        }
        for(int i = 0; i < s.length(); i++) {
            StringBuilder strB = new StringBuilder(4);
            StringBuilder strBDec = new StringBuilder(4);
            strB.append(s.substring(0, i));strBDec.append(s.substring(0, i));
            strB.append( (s.charAt(i)  == '9')?'0':((char)(s.charAt(i) + 1)));
            strBDec.append( (s.charAt(i)  == '0')?'9':((char)(s.charAt(i) - 1)));
            strB.append(s.substring(i+1, s.length()));strBDec.append(s.substring(i+1, s.length()));
            String ss1 = strB.toString();
            String ss2 = strBDec.toString();
            if (!dSet.contains(ss1)) {
                childSet.add(ss1);
            }
            if (!dSet.contains(ss2)) {
                childSet.add(ss2);
            }
            if (index < MAX_INDEX) {
                if (dSet.contains(ss1)) {
                    System.out.print(ss1 + " In deadend skipping");
                }
                if (dSet.contains(ss2)) {
                    System.out.print(ss2 + " In deadend skipping");
                }
            }
        }
        if (index < MAX_INDEX) {
            for (String xx : childSet) System.out.print(xx + " ");
        }
        return childSet;
    }


    public static void main(String[] args) {
        OpenTheLock otl = new OpenTheLock();
        //String[] iA1 = {"0201","0101","0102","1212","2002"};
        String[] iA1 = {"8888"};
        System.out.println(otl.openLock(iA1,"0009"));
    }

}
