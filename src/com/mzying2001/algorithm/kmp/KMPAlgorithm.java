package com.mzying2001.algorithm.kmp;

public class KMPAlgorithm {
    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        System.out.println("index = " + kmpSearch(str1, str2));

    }

    public static int[] getNextArr(String str) {

        int[] next = new int[str.length()];
        next[0] = 0;

        for (int i = 1, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j))
                j = next[j - 1];
            if (str.charAt(i) == str.charAt(j))
                j++;
            next[i] = j;
        }
        return next;
    }

    public static int kmpSearch(String str1, String str2) {
        int[] next = getNextArr(str2);
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j))
                j = next[j - 1];
            if (str1.charAt(i) == str2.charAt(j) && ++j == str2.length())
                return i - j + 1;
        }
        return -1;
    }
}
