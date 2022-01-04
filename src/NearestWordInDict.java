import java.util.*;

/*
   There are multiple words in dictionary or file. A word can be transformed by changing one alphabet at a time with all words need to
   be in the dict.
   Print the shortest part between words.
   Basically a graph use bit array.
   Use Breadth first search.
 */
public class NearestWordInDict {
    final boolean nearestDist[][];
    final Map<String,Integer> mWordToIndex;
    final Map<Integer,String> mIndexToWord;
    final Queue<GNode> wordOfInt;


    //Assume list is not null nor does it have null string.
    public NearestWordInDict(List<String> listOfWord) {
        nearestDist = new boolean[listOfWord.size()][listOfWord.size()];
        mWordToIndex = new HashMap<>();
        mIndexToWord = new HashMap<>();
        populateGraphAndIndex(listOfWord);
        wordOfInt  = new ArrayDeque<>();
    }

    private void populateGraphAndIndex(List<String> listOfWord) {
        for(int i = 0; i < listOfWord.size();i++) {
            String outerStr = listOfWord.get(i);
            mWordToIndex.put(outerStr,i);
            mIndexToWord.put(i,outerStr);
            for(int j = 0; j < listOfWord.size();j++) {
                if (i == j) continue;
                String innerStr = listOfWord.get(j);
                nearestDist[i][j] = isOneAlphaDiff(outerStr,innerStr);
            }
        }//End of outer for
    }

    /* Assume any string which are not same length are inherently not one alpha diff */
    private boolean isOneAlphaDiff(String s1,String s2) {
        int diff = 0;
        if (s1.length() != s2.length()) return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for(int i = 0; i < s1.length();i++) {
            if (c1[i] != c2[i]) diff++;
            if (diff > 1) return false;
        }
        return true;
    }


    class GNode{
        String s;
        List<String> nList = new ArrayList<>();//Starting from parent.
    }

    public void printSmallestDistance(String s1,String s2) {
        if ((s1 == null) || (s2 == null) || (s1.length() == 0) || (s2.length() == 0)) {
            System.out.println("Null");
            return;
        }
        if (s1.equals(s2)) {
            System.out.println("Word same");
            return;
        };
        GNode pNode = new GNode();
        pNode.s = s1;
        wordOfInt.add(pNode);
        while(!wordOfInt.isEmpty() ) {
            GNode cNode = wordOfInt.poll();
            if (cNode.s.equals(s2) ) {
                printNodePath(cNode);
                return;
            } else {
                List<String> nearest = getNearest(cNode.s);
                for(String ss : nearest) {
                    GNode nodeNew = new GNode();
                    nodeNew.s = ss;
                    nodeNew.nList.addAll(cNode.nList);
                    nodeNew.nList.add(cNode.s);
                    wordOfInt.add(nodeNew);
                }
            }
        }//End of while
        System.out.println("No path between "+s1+" and "+s2);
    }//End of printSmallestDistance

    private void printNodePath(GNode g) {
        for(String s : g.nList) {
             System.out.print(s);
             System.out.print(" ");
        }
        System.out.print(g.s);
    }

    private List<String> getNearest(String s) {
        int row = mWordToIndex.get(s);
        List<String> retList = new ArrayList<>();
        for(int j = 0; j < nearestDist[0].length;j++) {
            if (nearestDist[row][j]) {
                retList.add(mIndexToWord.get(j));
            }
        }
        return retList;
    }

    public static void main(String[] args) {
        String[] sArray = new String[]{"bam","ras","bas","bat"};
        NearestWordInDict nWord = new NearestWordInDict(Arrays.asList(sArray));
        nWord.printSmallestDistance("bam","bat");

    }

}