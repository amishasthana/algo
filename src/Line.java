public class Line {
    double m = 0; double a = 0;
    public boolean checkStraightLine(int[][] coordinates) {
           if ((coordinates == null)  || (coordinates.length <= 2)) return true;
           calculateSlope(coordinates);
        int x1 = coordinates[0][0];
        int y1 = coordinates[0][1];
           for(int i = 2;i < coordinates.length;i++) {
               int x2 = coordinates[i][0];
               int y2 = coordinates[i][1];
                if (m == Double.POSITIVE_INFINITY) {
                    if (x2  != x1) return false;
                } else if (m == 0) {
                    if (y2  != y1) return false;
                } else {
                    double mi = ((y2 - y1)*1.0)/(x2-x1); double ai = y2 - (m*x2);
                    if ((m  != mi)  || (a != ai))
                        return false;
                }
           }
           return true;
    }

    private void calculateSlope(int[][] coordinates) {
        int x1 = coordinates[0][0];
        int y1 = coordinates[0][1];
        int x2 = coordinates[1][0];
        int y2 = coordinates[1][1];
        if (x1 == x2) {
            m = Double.POSITIVE_INFINITY;
        } else if (y1 == y2) {
           m = 0;
        } else {
            m = ((y2 - y1)*1.0)/(x2-x1);
            a = y1 - (m*x1);
        }

    }

    public static void main(String[] args) {
        //int[][] iA = {{1,1},{2,2},{3,4},{4,5},{5,6},{7,7}};
        int[][] iA = {{-3,-2},{-1,-2},{2,-2},{-2,-2},{0,-2}};
        Line l = new Line();
        System.out.print(l.checkStraightLine(iA));
    }
}
