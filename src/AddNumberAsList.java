
/*
Class to add two number which are stored as list
*/

public class AddNumberAsList {

    class Node {
        Node next;
        int v;
    }

    //Assume list start from unit position. So 657 ---> Actually mean the number is 756.
    public int addTwoList(Node l1,Node l2) {
        Node  list1 = l1;
        Node  list2 = l2;
        int totalCount = 0;
        int pos = 0;
        int carryOver = 0;
        while((list1 != null) || (list2 != null)) {
            int cSum = (getCurrentElement(list1) + getCurrentElement(list2) + carryOver);
            carryOver = cSum/10;
            cSum = cSum %10;
            totalCount += (cSum*(int) Math.pow(10, pos));
            list1 = (list1 == null)?list1:list1.next;
            list2 = (list2 == null)?list2:list2.next;
            pos++;
        }
        return totalCount;
    }

    private int getCurrentElement(Node list) {
        int retValue = 0;
        if(list != null) {
            retValue = list.v;
        }
        return retValue;
    }

    public Node createLinkedList(int[] iArray) {
        Node startNode = null;
        Node cNode = null;
        if ((iArray != null) && (iArray.length != 0)) {

            for(int i = 0;i < iArray.length;i++) {
                Node nNode = new Node();
                nNode.v = iArray[i];
                if (startNode == null) {
                    startNode = nNode;
                    cNode = nNode;
                } else {
                    cNode.next = nNode;
                    cNode = nNode;
                }
            }
        }
        return startNode;
    }


    public static void main(String[] args) {
        AddNumberAsList addAsList = new AddNumberAsList();

        Node n1 = addAsList.createLinkedList(new int[]{1,3,5});
        Node n2 = addAsList.createLinkedList(new int[]{2,9});
        System.out.println(addAsList.addTwoList(n1,n2));
    }





}// End of class.