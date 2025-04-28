package com.les05;

public class demo {

    public static void main(String[] args) {
        demo demo = new demo();
        boolean flag = demo.repeatedSubstringPattern("abcabcabc");

        System.out.println(flag);
    }

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int i = 1; i * 2 <= n; i++) {

            if (n % i == 0) {
                boolean flag = true;
                for (int j = i; j < n; j++) {
                    if (s.charAt(j) != s.charAt(j-i)) {
                        flag = false;
                    }
                }

                if (flag) {
                    return true;
                }
            }

        }

        return false;
    }

}
