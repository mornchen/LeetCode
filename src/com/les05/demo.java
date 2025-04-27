package com.les05;

public class demo {

    public static void main(String[] args) {
        demo demo = new demo();
        boolean flag = demo.repeatedSubstringPattern("hellohello");

        System.out.println(flag);
    }

    public boolean repeatedSubstringPattern(String s) {
        char[] chars = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }


        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j < s.length(); j++) {
                if (chars[i] == chars[j] && j < s.length() - 1) {
                    for (int k = i + 1; k < j; k++) {
                        if (chars[k] != chars[j+k] && (j+k) < (s.length() - 1)) {
                            break;
                        }
                    }
                }

            }
        }

        return false;
    }

}
