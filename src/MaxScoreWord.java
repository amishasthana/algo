/*
1255. Maximum Score Words Formed by Letters

    User Accepted: 709
    User Tried: 811
    Total Accepted: 729
    Total Submissions: 1019
    Difficulty: Hard

Given a list of words, list of  single letters (might be repeating) and score of every character.

Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).

It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.



Example 1:

Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
Output: 23
Explanation:
Score  a=1, c=9, d=5, g=3, o=2
Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
Words "dad" and "dog" only get a score of 21.

Example 2:

Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
Output: 27
Explanation:
Score  a=4, b=4, c=4, x=5, z=10
Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
Word "xxxz" only get a score of 25.

Example 3:

Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
Output: 0
Explanation:
Letter "e" can only be used once.



Constraints:

    1 <= words.length <= 14
    1 <= words[i].length <= 15
    1 <= letters.length <= 100
    letters[i].length == 1
    score.length == 26
    0 <= score[i] <= 10
    words[i], letters[i] contains only lower case English letters.


 */
public class MaxScoreWord {
    //Map<Character,Integer> mChars = new HashMap<>();
    int[] nOfElem = new int[26];
    int[] score = null;
    public int maxScoreWords(String[] words, char[] letters, int[] score) {

         canSatisfy(letters,true);
         this.score = score;

         return figureOutMax(words,0,0);
    }

    /*
         words --> List of word
         index --> Index of that word.
         total so far.
         nOfCurrentElem.
     */
    private int figureOutMax(String[] words,int index,int scoreSoFar) {
            if (index >= words.length) return scoreSoFar;
            int scoreWithWord = -1;
            int scoreWithoutWord = -1;

            if (canSatisfy(words[index].toCharArray(),false)) {
                scoreWithWord = scoreSoFar+figureOutMax(words,index+1,calculateScore(words[index].toCharArray()));
            }
            canSatisfy(words[index].toCharArray(),true);//Removeif (canSatisfy(words[index].toCharArray(),false)) {
            scoreWithoutWord = figureOutMax(words,index+1,scoreSoFar);
        scoreSoFar = Math.max(scoreSoFar,scoreWithWord);
        scoreSoFar = Math.max(scoreSoFar,scoreWithoutWord);
        return scoreSoFar;

    }

    private int calculateScore(char[] cArr) {
        int scoreCal = 0;
        //for(int i = 0; i < 26; i++) {
        for(char c: cArr) {
            int i = c - 'a';
            scoreCal += score[i];
        }
        return scoreCal;
    }


    private boolean canSatisfy(char[] charArr,boolean add) {
        boolean retV = true;
        for(char c: charArr) {
            int index = c - 'a';
            if (add) {
                nOfElem[index] = nOfElem[index] + 1;
            } else {
                nOfElem[index] = nOfElem[index] - 1;
            }
            if (nOfElem[index] < 0) {
                retV = false;
            }
        }
        return retV;
    }

    public static void main(String[] args) {
        String[] words = {"xxxz","ax","bx","cx"};
        //String[] words = {"xxxz",};
        char[] letters = {'z','a','b','c','x','x','x'};
        int[] score = {4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10};
        MaxScoreWord msc = new MaxScoreWord();
        System.out.print(msc.maxScoreWords(words,letters,score));
    }


}
