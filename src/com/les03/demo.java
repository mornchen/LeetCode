package com.les03;

public class demo {

    public static void main(String[] args) {
        String haystack = "1234nbmabc";
        String needle = "abc";

        demo dm = new demo();
        int result = dm.strStr(haystack, needle);
        System.out.println(result);
    }


    public int strStr(String haystack, String needle){
        int m = haystack.length();
        int n = needle.length();

        if (n == 0) {
            return 0;
        }

        for(int i = 0; i <= m - n; i++) {

            int j;
            for(j = 0; j < n; j++) {
                if(haystack.charAt(i + j) == needle.charAt(j)) {
                    continue;
                }
                break;
            }

            if (j == n) {
                return i;
            }
        }

        return -1;
    }
}