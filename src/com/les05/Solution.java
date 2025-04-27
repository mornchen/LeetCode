package com.les05;

public class Solution {

    public static void main(String[] args) {
        Solution sl = new Solution();

        boolean flag = sl.repeatedSubstringPattern("abcabcbb");
        System.out.println(flag);
    }

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        // 遍历所有可能的子串长度（从1到n/2）
        for (int i = 1; i * 2 <= n; ++i) {
            // 只有当n能被i整除时，才可能有重复的子串
            if (n % i == 0) {
                boolean match = true;
                // 检查每个位置是否满足重复模式
                for (int j = i; j < n; ++j) {
                    // 比较当前字符与子串对应位置的字符
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                // 如果所有位置都匹配，返回true
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }
}
