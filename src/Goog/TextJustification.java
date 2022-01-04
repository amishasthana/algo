package Goog;

import java.util.LinkedList;
import java.util.List;

/*
68. Text Justification
Hard

Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

    A word is defined as a character sequence consisting of non-space characters only.
    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    The input array words contains at least one word.

Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.

Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]


 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new LinkedList<>();
        if ( (words == null) || (words.length == 0) ) return list;
        int n = words.length;
        int[] listRef = new int[n];
        int wUsed = 0;
        int listN = 0;
        for(int i = 0; i < n; i++) {
            String s = words[i];
            if ( ( s.length()  + wUsed) <= maxWidth ) {
                wUsed += s.length()+1;
            } else {
                wUsed = s.length()+1;
                listN++;
            }
            listRef[i] = listN;
        }

        for(int i = 0; i < n; ) {
            int cVal = listRef[i] ; int start = i;
            int end = start;
            while ((i < n) && (cVal == listRef[i]) ) {
                end = i++;
            }
            boolean lastLine = (listRef[n-1] == cVal);
            String ss = null;
            if (start == end) {
                ss = format(words,start,end,maxWidth,true);//words[start];
            } else {
                ss = format(words,start,end,maxWidth,lastLine);
            }
            list.add(ss);
        }
        return list;

    }

    private String format(String[] words,int s,int e, int maxWidth,boolean lastLine) {
        StringBuilder strB = new StringBuilder();
        if (lastLine) {
            int len = 0;
            for(int i = s; i <= e;i++) {
                strB.append(words[i]);
                len += words[i].length();
                if (len < maxWidth) {
                    strB.append(" "); len++;
                }
            }
            while (len++ < maxWidth) strB.append(" ");
        } else {
            int len = 0;
            for(int i = s; i <= e;i++) {
                len += words[i].length();
            }
            int minGap = (maxWidth - len)/(e-s);
            int extraGap = maxWidth - ((e-s)*minGap+len);
            for(int i = s; i <= e; i++) {
                strB.append(words[i]);
                if (i == e) break;
                for( int j = 0; j < minGap;j++) {
                    strB.append(" ");
                }
                if (extraGap-- > 0 ) {
                    strB.append(" ");
                }
            }
        }
        return strB.toString();


    }

    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
        String[] s = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] s1 = {"What","must","be","acknowledgment","shall","be"};
        String[] s2 = {"Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do"};
        List<String> ll = tj.fullJustify(s,16);
        for(String ss : ll) System.out.println(ss);

    }

}
