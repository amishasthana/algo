import java.util.ArrayDeque;
import java.util.Queue;

public class Bracket {
    /*
        Given n number seeded player, break into bracket.
        The idea is to seed 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
         You will get a tree. If not power of 2, then add buffer at end.

         Two step :
         a) Form set 0-> N-1
                     1 --> N-2 etc. So divide the set in n/2 and just do it.
         b) Which sets will be together. One way is alternate.

     */

      class Node {
            Node left;
            Node right;
            int x;
            int y;
      }

    Node root = null;


      /* Root aka Final */
      public Node getBrackets(int n) {
           if (n <= 1) {
               throw new IllegalArgumentException("Please provide a number greater then 1");
           }
           int twoPower = findTwoPower(n);
           double totalCount = Math.pow(2.0,twoPower*1.0);
           int[] aPla = new int[(int)totalCount];
           for(int i = 0; i < totalCount;i++) {
               if (i < n) {
                       aPla[i] = i;
               } else {
                   aPla[i] = -1;//No player
               }
           }
           Node[] nodeArray = findTheFirstRoundMatch(aPla);
           createTreeBracket(nodeArray);
           printTree(root);
           return root;
      }

      private void printTree(Node n) {
          if ( n == null) return;
          Queue<Node>  nQ = new ArrayDeque<>();
          nQ.add(n);
          int i = 0;
          while (!nQ.isEmpty()) {
                 Node cNode = nQ.poll();
                 if ((cNode.x != 0) && (cNode.y != 0)) {
                     System.out.print(cNode.x + "," + cNode.y + " ");
                 }
                 if (cNode.left != null) {
                     nQ.add(cNode.left);
                 }
              if (cNode.right != null) {
                  nQ.add(cNode.right);
              }
              /*
                 if ((i > 0 && ((i & (i - 1)) == 0)))  {//Stack overflow to figure out quick way for power of two.
                  System.out.println();
              }
              */
          }
      }



      private void createTreeBracket(Node[] nodeTree) {
          if (nodeTree.length == 1) {
              root = nodeTree[0];
              return;
          }
          Node[] nArray = new Node[nodeTree.length/2];
          for(int i = 0;i < (nodeTree.length/2);i++) {
              Node cLeftNode = nodeTree[i];
              Node cRightNode = nodeTree[nodeTree.length-i-1];
              Node rootNode = new Node();
              rootNode.left = cLeftNode;
              rootNode.right = cRightNode;
              nArray[i] = rootNode;
          }
          createTreeBracket(nArray);
      }

      private Node[] findTheFirstRoundMatch(int[] a) {
          Node[] nArray = new Node[a.length/2];
          for(int i = 0; i < a.length/2;i++) {
              Node n = new Node();
              n.x = (a[i] < 0)?a[i]:a[i]+1;
              int yIndex = a.length - i-1;
              n.y = (a[yIndex] < 0)?a[yIndex]:a[yIndex]+1;
              nArray[i] = n;
          }
          return nArray;
      }

      private int findTwoPower(int n) {
          int pow = 0;
          int org = n;
          while (true) {
              n = n/2;
              pow++;
              if (n == 0) {
                  break;
              }
          }
          if (Math.pow(2,(pow-1)) < org) {
              return pow;
          }
          return pow-1;
      }

      public static void main(String[] args) {
          Bracket bracket = new Bracket();
          bracket.getBrackets(31);
      }
}
