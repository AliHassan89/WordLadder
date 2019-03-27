/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.


 */

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        System.out.println(ladderLength("hit", "cog", wordList));
    }


    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<QItem> q = new LinkedList<>();
        q.add(new QItem(beginWord, 1));

        while (!q.isEmpty()) {
            QItem item = q.poll();

            for(Iterator<String> it = wordList.iterator(); it.hasNext() ;) {
                String word = it.next();
                if (isAdjacent(item.getWord(), word)) {
                    int len = item.getLen();
                    q.add(new QItem(word, ++len));

                    if (word.equals(endWord)) {
                        return item.getLen() + 1;
                    }
                    it.remove();
                }
            }
        }

        return 0;
    }

    private static boolean isAdjacent(String w1, String w2) {
        char[] arr1 = w1.toCharArray();
        char[] arr2 = w2.toCharArray();

        int diff = 0;
        for (int i=0; i<arr1.length; i++) {
            if (arr1[i] != arr2[i])
                ++diff;
        }

        return diff == 1 ? true : false;
    }
}

class QItem {
    String word;
    int len;

    public QItem(String word, int len) {
        this.word = word;
        this.len = len;
    }

    public String getWord() {
        return this.word;
    }

    public int getLen() {
        return this.len;
    }
}
