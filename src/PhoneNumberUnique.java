import java.util.*;

/*
Started : 2:10
Round4:
Given an array which contains 1-9 keys in a phone dial pad. To traverse this key pad you can only move either
1 step up/down/left/right first and then 3 steps agian in up/down/left/right direction or vice versa.

Find out the number of unique destinations you can reach with a path of length 4 starting from a source key.
 Additionaly this dial pad has one disabled key (x), so you cannot take that path.

Example:
Input: [1,2,3,4,x,6,7,8,9] , Source: 3
Output: 7

Explanation:
1 2 3
4 x 6
7 8 9

Source: 3
path1 : 3->6->9->8
path2: 3->2->1->4

When there are no more paths left to explore from 3 so we explore the paths from 8 and 4 where we reached by traversing through 3

path3: 8->7->4->1
path4: 4->7->8->9

Again repeating the same steps we explore paths from 1 and 9
path5: 1->2->3->6
path6: 9->6->3->2

Then with 6 as next source
path7: 6->9->8->7

Note: The paths we traverse may have the same nodes but the destinations must be unique.


 We will put in Queue and also maintain a HashSet of unique int in a path.
 Then we take each end and try to see if we can get any new end point. Till we do not get any new end points.


 */
public class PhoneNumberUnique {


    class UniquePath{
        List<Integer> path = new ArrayList<>();
        Set<Integer> pathSet = new HashSet<>();
    }


    Set<Integer> endPointSet = new HashSet<>();
    Set<Integer> startPointSet = new HashSet<>();
    int xCell = 0;



    /*
         Start : Start element, l : Length of path.
     */
    public void printUniquePaths(int x,int start,int size) {
          xCell = x;

          Queue<Integer> startList = new ArrayDeque<>();
          startPointSet.add(start);
          startList.add(start);
          while (!startList.isEmpty()) {
              int startOfPath = startList.poll();
              Queue<UniquePath> qPath = new ArrayDeque<>();
              UniquePath rootPath = new UniquePath();
              rootPath.path.add(startOfPath);
              rootPath.pathSet.add(startOfPath);
              qPath.add(rootPath);
              while (!qPath.isEmpty()) {
                  UniquePath curr = qPath.poll();
                  if (curr.path.size() == size) {
                      int end = curr.path.get(curr.path.size() - 1);
                      if (!startPointSet.contains(end)) {
                          startList.add(end);
                          startPointSet.add(end);
                      }
                      if (!endPointSet.contains(end)) {
                          print(curr);
                          endPointSet.add(end);
                      }
                      continue;
                  }
                  Set<Integer> nElements = getNextElem(curr);
                  for (int nCelem : nElements) {
                      UniquePath newPath = new UniquePath();
                      newPath.path = new ArrayList<>(curr.path);
                      newPath.path.add(nCelem);
                      newPath.pathSet = new HashSet<>(curr.pathSet);
                      newPath.pathSet.add(nCelem);
                      qPath.add(newPath);
                  }
              }
          }// end of start list
    }

    private void print( UniquePath rootPath) {
        System.out.println("The unique paths are ");
        for(Integer i : rootPath.path) {
            System.out.print(i+" ");
            System.out.println("");
        }
    }

    private Set<Integer> getNextElem(UniquePath elem) {//Only give still unique.
        int i = elem.path.get(elem.path.size()-1);
        //Cell c = mapOfCell.get(i);
        Set<Integer> setOfCell = populateInteger(i);
        Set<Integer> setOfNextElem = new HashSet<>();
        for(Integer cc : setOfCell) {
            if (!elem.pathSet.contains(cc)) {
                setOfNextElem.add(cc);
            }
        }
        return setOfNextElem;
    }

    private Set<Integer> populateInteger(int i) {
        Set<Integer> setOfNextInteger = new HashSet<>();
        int bottom = i+3;
        int top = i-3;
        int left = -1; int right = -1;
        if( ((i-1)/3) == (i/3)) right = i+1;
        if( ((i-1)/3) == ((i-2)/3)) left = i-1;
        if( valid(bottom) ) setOfNextInteger.add(bottom);
        if( valid(top) ) setOfNextInteger.add(top);
        if( valid(left) ) setOfNextInteger.add(left);
        if( valid(right) ) setOfNextInteger.add(right);
        return setOfNextInteger;
    }

    private boolean valid(int i) {
        if ((i >= 1) && (i<= 9) && (i != xCell)) return true;
        return false;
    }

/*
    private Set<Cell> populateCell(Cell c) {
        Set<Cell> setOfNextCell = new HashSet<>();
        if (c.left != null) setOfNextCell.add(c.left);
        if (c.right != null) setOfNextCell.add(c.right);
        if (c.top != null) setOfNextCell.add(c.top);
        if (c.bottom != null) setOfNextCell.add(c.bottom);
        return setOfNextCell;
    }
    */

    public static void main(String[] args) {
          int[] iA = {1,2,3,4,5,6,7,8,9};
        PhoneNumberUnique pnu = new PhoneNumberUnique();
        pnu.printUniquePaths(5,3,4);
    }

}
