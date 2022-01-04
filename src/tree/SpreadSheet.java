package tree;

public class SpreadSheet {

    public static final String SEP = "|";
    public static final String EMPTY = "";
    public static final String SPACE = " ";
    private final String[][] spArray;
    private  final int[] colWidth;

    public SpreadSheet(int r, int c) {
        //No validation as of now.
        spArray = new String[r][c];
        for(int i = 0;i < spArray.length;i++) {
            for (int j = 0; j < spArray[0].length; j++) {
                spArray[i][j] = EMPTY;
            }
        }
        colWidth = new int[c];
        for(int j =0; j < colWidth.length;j++) {
            colWidth[j] = 0;
        }
    }

    public void putElement(int r, int c,String input) {
        spArray[r][c] = input;
        int size = (input == null)?0:input.length();
        if (colWidth[c] < size) {
            colWidth[c] = size;
        }
    }

    public void printSpreadsheet() {
        for(int i = 0;i < spArray.length;i++) {
            for(int j = 0;j < spArray[0].length;j++) {
                System.out.print(printPretty(spArray[i][j],j));
                if (j != (spArray[0].length-1)) {
                    System.out.print(SEP);
                }
            }
            System.out.println();
        }//End of row
    }

    public String printPretty(String s,int col) {
        int remSize = colWidth[col] - s.length();
        StringBuilder strB = new StringBuilder(s);
        for(int i = 0;i < remSize;i++) {
            strB.append(SPACE);

        }

        return strB.toString();
    }

    public static void main(String[] args) {
        SpreadSheet sp = new SpreadSheet(4,3);
        sp.putElement(0,0,"bob");
        sp.putElement(0,1,"10");
        sp.putElement(0,2,"foo");
        sp.putElement(1,0,"alice");
        sp.putElement(1,1,"5000");
        sp.putElement(1,2,"amish");
        sp.printSpreadsheet();
    }

}
