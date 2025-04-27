package com.les04;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
 */
public class Solution {

    public static void main(String[] args) {
        // 测试用例1：互为异位词
        String s = "anagram";
        String t = "nagaram";

        Solution sl = new Solution();
        System.out.println(sl.isAnagram(s, t)); // 预期输出：true
    }

    /**
     * 检测两个字符串是否为字母异位词
     * @param s 第一个字符串（仅包含小写字母）
     * @param t 第二个字符串（仅包含小写字母）
     * @return true表示是异位词，false表示不是
     */
    public boolean isAnagram(String s, String t) {
        // 1. 快速失败：长度不同直接返回false
        if (s.length() != t.length()) return false;

        // 2. 初始化字母计数器数组（26个英文字母）
        // 索引0对应'a'，索引25对应'z'
        int[] count = new int[26];

        // 3. 统计字符频率
        for (int i = 0; i < s.length(); i++) {
            // s.charAt(i) - 'a' 是计算字符 s.charAt(i) 与字符 'a' 的 Unicode 码点差值
            // 用于将字母映射到0-25的数组索引

            // 对s中的字符进行增量计数（+1）
            count[s.charAt(i) - 'a']++;  // 'a'-'a'=0，'z'-'a'=25
            // 对t中的字符进行减量计数（-1）
            count[t.charAt(i) - 'a']--;
        }

        // 4. 检查计数器是否全部归零
        for (int i = 0; i < 26; i++) {
            // 任一计数器非零说明字符频率不匹配
            if (count[i] != 0) return false;
        }

        return true;
    }
}
