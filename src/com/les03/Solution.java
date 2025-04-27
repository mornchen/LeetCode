package com.les03;

/**
 * 给你两个字符串 haystack 和 needle ，
 * 请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 **/

public class Solution {

    public static void main(String[] args) {
        // 测试用例1：正常匹配情况
        String haystack = "1234nbmabc";
        String needle = "abc";

        Solution sl = new Solution();
        int index = sl.strStr(haystack, needle);
        System.out.println(index); // 预期输出: 0

        // 测试用例2：不匹配情况
        System.out.println(sl.strStr("leetcode", "leeto")); // 预期输出: -1

        // 测试用例3：空needle情况
        System.out.println(sl.strStr("any", "")); // 预期输出: 0
    }

    /**
     * 字符串匹配方法（暴力匹配算法）
     * @param haystack 被搜索的主字符串
     * @param needle 要查找的子字符串
     * @return 子字符串第一次出现的位置索引（从0开始），未找到返回-1
     * */
    public int strStr(String haystack, String needle) {
        // 获取两个字符串的长度
        int m = haystack.length(); // 主字符串长度
        int n = needle.length();   // 子字符串长度

        // 处理特殊情况：如果子字符串为空，直接返回0
        if (n == 0) {
            return 0;
        }

        // 主循环：遍历主字符串所有可能的起始位置
        // 注意：i的最大值是m-n，因为剩余字符不足时无需比较
        for (int i = 0; i <= m - n; i++) {
            // 内层循环：比较子字符串的每个字符
            int j; // 声明在循环外，用于判断是否完全匹配
            for (j = 0; j < n; j++) {
                // 如果发现不匹配的字符，立即跳出内层循环
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }

            // 检查是否完全匹配：如果j等于子字符串长度，说明所有字符都匹配
            if (j == n) {
                return i; // 返回当前起始位置
            }
        }

        // 循环结束仍未找到匹配，返回-1
        return -1;
    }
}
