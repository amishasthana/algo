package tree;

public class ValidateBst {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
/*
[10,5,15,3,7,6,20]

                                         A


           B (B < A)                                   E ( E > A)


       C        D
                ( D > B and D < A)                 F                    G
       C < B                                   ( F < E, F > A)        ( G > E and  ....)

       A --> 10, MIN, MAX
       B -->   5, -,10
       C --> 3 ,-1,5
       D --> 7, 5,10


       A --> 10, MIN, MAX
       E --> 15, 10,+
       F  --> 6, 10, 15
       G --> 20,15,+


 */
    long MIN = -2147483649l;
    long MAX = 2147483649l;
    public boolean isValidBST(TreeNode root) {
         if (root == null) return true;

         return validate(root,MIN,MAX);
    }




    private boolean validate(TreeNode root,long minV,long maxV) {
        if (root == null) return true;
        //boolean compRoot = moreThen(root.val,(long)minV) && lessThen(root.val,maxV);
        boolean compRoot = ((root.val > minV) && (root.val < maxV));
        System.out.println("Root is "+root.val+ " comp Root "+compRoot);
        return compRoot && validate(root.left,Math.min(root.val,minV),root.val) && validate(root.right,root.val,Math.max(maxV,root.val));
    }
    /* ((root.val > minV) && (root.val < maxV)) */
    public boolean lessThen(int v1,int v2) {
        if ((v1==v2) && ((v2 == Integer.MAX_VALUE) || (v2 == Integer.MIN_VALUE))) return true;
        return (v1 < v2);
    }

    public boolean moreThen(int v1,int v2) {
        if ((v1==v2) && ((v2 == Integer.MAX_VALUE) || (v2 == Integer.MIN_VALUE))) return true;
        return (v1 > v2);
    }

    public TreeNode init() {
        /*
        TreeNode root = new TreeNode(10);
        TreeNode five = new TreeNode(5);
        TreeNode fifteen = new TreeNode(15);
        root.left = five; root.right = fifteen;
        TreeNode six = new TreeNode(6);
        TreeNode twenty = new TreeNode(20);
        fifteen.left = six;
        fifteen.right = twenty;
        */
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        return root;
    }

    public static void main(String[] args) {
        ValidateBst vbs = new ValidateBst();
        TreeNode tn = vbs.init();
        System.out.println(vbs.isValidBST(tn));
        System.out.print(Integer.MAX_VALUE);
    }
}
