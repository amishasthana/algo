import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/*
841. Keys and Rooms
Medium

There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.

Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.
A key rooms[i][j] = v opens the room with number v.

Initially, all the rooms start locked (except for room 0).

You can walk back and forth between rooms freely.

Return true if and only if you can enter every room.

Example 1:

Input: [[1],[2],[3],[]]
Output: true
Explanation:
We start in room 0, and pick up key 1.
We then go to room 1, and pick up key 2.
We then go to room 2, and pick up key 3.
We then go to room 3.  Since we were able to go to every room, we return true.

Example 2:

Input: [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can't enter the room with number 2.


Solution: Start from 0th. Do DFS. If any new increment the count. If N or if queue has none make the decision.

 */
public class RoomsVisited {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if ((rooms == null) || (rooms.size() <=1)) {
            return true;
        }
        int visited = 0;
        boolean[] visitArr = new boolean[rooms.size()];
        Queue<List<Integer>> qVisit = new ArrayDeque<>();
        qVisit.add(rooms.get(0));
        visited++;
        visitArr[0] = true;
        while (!qVisit.isEmpty()) {
            List<Integer> cElem = qVisit.poll();
            if (cElem != null) {
                for(int cRoom : cElem) {
                    if (visitArr[cRoom]) continue;
                    visited++;
                    if (visited >= rooms.size()) return true;
                    visitArr[cRoom] = true;
                    qVisit.add(rooms.get(cRoom));
                }
            }
        }
       return false;
    }

    public static void main(String[] args) {

    }
}
