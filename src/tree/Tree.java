package tree;

/*
A basic class which create tree. It takes an array of integer and create tree.
If the array is sorted it will create a binary tree.
 */
public class Tree {

    public class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int v;
    }

    private int[] intOfValues = null;
    public TreeNode mainRoot;

    public TreeNode createTreeFromArray(int[] aA) {
        intOfValues = aA;
        return createTree(0,aA.length-1);
    }


    /*
        Assume cNode is not null
        pos is the  postion of cNode in current array
     */

    private TreeNode createTree(int startPos,int endPos) {//Start and end pos withing which we are creating.These are indexes inclusive
        if (startPos > endPos) {// Not equalto, create a single node when they are same.
            return null;
        }
        TreeNode cNode = new TreeNode();
        int mIndex = startPos+(endPos-startPos)/2;// 2,2 -> 0 , 2,3--> 0, 2,4--> 1 , 2,5--> 1
        cNode.v = intOfValues[mIndex]; //2,2 -> 2 , 2,3--> 2, 2,4--> 3 , 2,5--> 3

        cNode.left = createTree(startPos,mIndex-1);//2,1 ,2,1,, 2,2 , 2,2
        cNode.right = createTree(mIndex+1,endPos);//3,2 , 3,3, 4,4 , 4,5

        return cNode;
    }

    public static void main(String[] arrgs) {
        Tree tree = new Tree();
        int[] a = {0,1,2,3,4,5,6,7,8,9};
        //int[] a = {0,1,2};
        TreeNode treeNode = tree.createTreeFromArray(a);
        System.out.println("treeNode "+treeNode.v);
    }



}
