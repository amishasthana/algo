import java.util.*;
public class LiftOffTwo {
    /*
    int[] getConnections(int userID)
getConnections returns a list of IDs of users who are directly connected to the user with the provided ID. You may assume getConnections is implemented for you.

Example
Given this graph:


Calling getConnections(B) returns the set of IDs for users {A, U, C, E, D}. getConnections(V) returns the set of IDs for users {D, E}.

Sample expected outputs:

getDistance(U,V) => 3
getDistance(B,E) => 1
     */
    int[][] gArray = null;
    int[] iArr = {0,1,2,3,4,5,6};

    String[] nArr = {"A","B","C","D","E","U","V"};

    public LiftOffTwo() {
        initalize();
    }

    private void initalize() {
        //gArray = new int[iArr.length][iArr.length];
        int[][] g ={
                {0,1,0,0,0,0,0},
                {1,0,1,1,1,1,0},
                {0,1,0,0,1,1,0},
                {0,1,0,0,1,0,1},
                {0,1,1,1,0,0,1},
                {0,1,1,0,0,0,0},
                {0,0,0,1,1,0,0}
        };
        this.gArray = g;
    }

    int[] getConnections(int userID) {

      return gArray[userID];
    }


    private Set<String> getAllChildNode(String p) {
        int index = -1;
        for(int i = 0; i < nArr.length;i++ ) {
            if(nArr[i].equals(p)) {
                index = i; break;
            }
        }
        if (index == -1) return null;//
         int[] child = getConnections(index);
         Set<String> childs = new HashSet<>();
        for(int j = 0; j < child.length; j++) {
            if (child[j] == 1) childs.add(nArr[j]);
        }
        return childs;
    }

    /*

     */
    public int getDistance(String  src,String target) {
        //initalize();
        int distOne = 0;
        int distTwo = 0;
        if (src.equals(target) ) return distOne;
        Queue<String> qOfNodes = new LinkedList<>();
        qOfNodes.add(src);
        Queue<String> qOfNodesTwo = new LinkedList<>();
        qOfNodesTwo.add(target);
        Set<String> vSetOne = new HashSet<>();
        vSetOne.add(src);
        Set<String> vSetTwo = new HashSet<>();
        vSetOne.add(target);

        while (!qOfNodes.isEmpty() || !qOfNodesTwo.isEmpty()) {
            while (!qOfNodes.isEmpty()) {
                int nOfNodes = qOfNodes.size();
                int index = 0;
                distOne++;
                while (index < nOfNodes) {
                    String cParent = qOfNodes.poll();
                    Set<String> cNodes = getAllChildNode(cParent);
                    for (String child : cNodes) {
                        if (child.equals(target)) return distOne;
                        if (vSetOne.contains(child)) continue;
                        qOfNodes.add(child);
                        vSetOne.add(child);
                    }
                    index++;
                }
            }

            while (!qOfNodesTwo.isEmpty()) {
                int nOfNodes = qOfNodesTwo.size();
                int index = 0;
                distTwo++;
                while (index < nOfNodes) {
                    String cParent = qOfNodesTwo.poll();
                    Set<String> cNodes = getAllChildNode(cParent);
                    for (String child : cNodes) {
                        if (child.equals(target)) return distTwo;
                        if (vSetTwo.contains(child)) continue;
                        qOfNodesTwo.add(child);
                        vSetTwo.add(child);
                    }
                    index++;
                }
            }

            for(String s : vSetOne ) {
                if (vSetTwo.contains(s)) return distOne+distTwo;
            }

        }
        return -1;

    }

    public static void main(String[] args) {
        LiftOffTwo lt = new LiftOffTwo();
        System.out.println(lt.getDistance("U","B"));
    }


}
