package Goog;

public class WordInRow {
    int nLine = 0;
    public int wordsTyping(String[] sentence, int rows, int cols) {
        if ( ( sentence == null) || (sentence.length == 0) ) return 0;
        solve(sentence, rows,cols);
        return rows/nLine;
    }

    public void solve(String[] sentence, int rows, int cols) {
        int tLen = 0; boolean allC = true;
        for(int i = 0; i < sentence.length; i++) {
            int nn = sentence[i].length();
            allC = true;
            tLen += nn+1;
            if ( tLen > cols) {
                nLine++; tLen = nn+1; allC = false;
            }
        }
        if (!allC) nLine++;
    }

    public static void main(String[] args) {
        String[] A = {"a","bcd","e"};
        WordInRow wr = new WordInRow();
        System.out.println(wr.wordsTyping(A,3,6));
    }


}
