import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/*
1263. Minimum Moves to Move a Box to Their Target Location

    User Accepted: 159
    User Tried: 376
    Total Accepted: 166
    Total Submissions: 794
    Difficulty: Hard

Storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.

The game is represented by a grid of size n*m, where each element is a wall, floor, or a box.

Your task is move the box 'B' to the target position 'T' under the following rules:

    Player is represented by character 'S' and can move up, down, left, right in the grid if it is a floor (empy cell).
    Floor is represented by character '.' that means free cell to walk.
    Wall is represented by character '#' that means obstacle  (impossible to walk there).
    There is only one box 'B' and one target cell 'T' in the grid.
    The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
    The player cannot walk through the box.

Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.



Example 1:

Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#",".","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 3
Explanation: We return only the number of times the box is pushed.

Example 2:

Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#","#","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: -1

Example 3:

Input: grid = [["#","#","#","#","#","#"],
               ["#","T",".",".","#","#"],
               ["#",".","#","B",".","#"],
               ["#",".",".",".",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 5
Explanation:  push the box down, left, left, up and up.

Example 4:

Input: grid = [["#","#","#","#","#","#","#"],
               ["#","S","#",".","B","T","#"],
               ["#","#","#","#","#","#","#"]]
Output: -1



Constraints:

    1 <= grid.length <= 20
    1 <= grid[i].length <= 20
    grid contains only characters '.', '#',  'S' , 'T', or 'B'.
    There is only one character 'S', 'B' and 'T' in the grid.

C++

 */
public class MaxStepsWithBlockAndConstraint {
    //private static Set<Character> BSET = new HashSet<>();
    //private static Set<Character> BSETFORP = new HashSet<>();
    char[][] grid = null;
    Coord box = null;
    Coord player = null;
    Coord target = null;
    /*
    static {
        BSET.add('#');
        BSETFORP.add('#');
        BSETFORP.add('B');
    }
    */

    Coord[][] coordArray = null;

    public int minPushBox(char[][] grid) {
         coordArray = new Coord[grid.length][grid[0].length];
         this.grid = grid;
         for(int i = 0; i < grid.length; i++) {
              for(int j = 0; j < grid[0].length; j++) {
                  coordArray[i][j] = new Coord(i,j);
                  if (grid[i][j] == 'B') {
                      box = coordArray[i][j];
                  } else if (grid[i][j] == 'T') {
                      target = coordArray[i][j];
                  } else if (grid[i][j] == 'S') {
                      player =coordArray[i][j];
                  }
              }
          }
          return canReach(box,target,false);
    }

    class Coord {
        int x;
        int y;
        int v;//value like visited.
        int pathV;//pathV for Player movement

        public Coord(int x,int y) {
            this.x = x;
            this.y = y;
        }


    }

    /*
         Method which will find min path between x/y1 to x2/y2, given the grid(class var)
         and character set.-1 if no path.
          c1-->x1/y1 --> Start pos
          c2-->x2/y2 --> End pos
     */
    private int canReach(Coord c1,Coord c2,boolean isPlayer) {
        Queue<Coord> coorVisited = new ArrayDeque<>();
        int minV = 0;
        coorVisited.add(c1);
        boolean found = false;
        while (!coorVisited.isEmpty()) {
            initalizePathOfPlayer();
            Coord c = coorVisited.poll();
            if ((c.x == c2.x) && (c.y == c2.y)) {
                found = true;
                break;
            }
            if (!isPlayer) {
                c.v = 1;
            } else {
                c.pathV = 1;
            }
            box = c;
            Set<Coord> vChild = getAllChild(c);
            for(Coord child : vChild) {
                if (child.v == 1) continue;
                if (isPlayer || canReach(c,child)) {
                    coorVisited.add(child);
                }
            }
            minV++;
        }
        if (found) {
            return minV;
        }
        return -1;
    }

    private void initalizePathOfPlayer() {
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                coordArray[i][j].pathV = 0;
            }
        }
    }

    /*
    private Set<Coord> getValidChild(Coord c) {
        Set<Coord> allChild = getAllChild(c);
        Set<Coord> vChild =  new HashSet<>();
        for(Coord cc : allChild) {
            if (cc.v == 1) continue;
            if (canReach(cc)) {
                vChild.add(cc);
            }
        }
        return vChild;
    */

    private boolean canReach(Coord box,Coord target) {
        Coord posToReach = getPosToReach();
        //if(isValid(posToReach.x,posToReach.y)) {
            if (canReach(player,posToReach,true) >= 0) {
                return true;
            }
        //}
        return false;
    }


    private Coord getPosToReach() {
        int x1 = 0; int y1 = 0;
        if (box.x < player.x) {
            return coordArray[box.x-1][box.y];
        } else if (box.x > player.x) {
            return coordArray[box.x+1][box.y];
        } else if (box.y < player.y) {
            return coordArray[box.x][box.y-1];
        } else {
            return coordArray[box.x][box.y+1];
        }
    }


    private Set<Coord> getAllChild(Coord c) {
        Set<Coord> allChild = new HashSet<>();
        Coord c1 = get(c.x+1,c.y);
        if (c1 != null) allChild.add(c1);
        Coord c2 = get(c.x-1,c.y);
        if (c2 != null) allChild.add(c2);
        Coord c3 = get(c.x,c.y+1);
        if (c3 != null) allChild.add(c3);
        Coord c4 = get(c.x,c.y-1);
        if (c4 != null) allChild.add(c4);
        return allChild;
    }

    private boolean isValid(int x,int y) {
        if ((x >= 0) && (x < grid.length) && (y>=0) && (y < grid[0].length)) {
            return false;
        }
        return true;
    }

    private Coord get(int x,int y) {
        if (isValid(x,y)) {
            return null;
        }
        if((grid[x][y] == '#') || (grid[x][y] == 'B')) return null;
        return coordArray[x][y];
    }

    public static void main(String[] args) {
        char[][] grid = {{'#','#','#','#','#','#'},
                         {'#','T','#','#','#','#'},
                         {'#','.','.','B','.','#'},
                         {'#','.','#','#','.','#'},
                         {'#','.','.','.','S','#'},
                         {'#','#','#','#','#','#'}};
        MaxStepsWithBlockAndConstraint msw = new MaxStepsWithBlockAndConstraint();
        System.out.println(msw.minPushBox(grid));
    }



}
