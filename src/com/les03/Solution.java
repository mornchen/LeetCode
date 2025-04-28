package com.les03;

/**
 * 字符串匹配工具类（暴力匹配算法实现）
 * 功能：在haystack中查找needle第一次出现的位置
 * 算法特点：时间复杂度O(m*n)，空间复杂度O(1)
 */
public class Solution {

    /**
     * 测试入口
     * 包含三类测试用例：
     * 1. 正常匹配情况
     * 2. 不匹配情况
     * 3. 边界条件（空needle）
     */
    public static void main(String[] args) {
        // 测试用例1：正常匹配（needle位于haystack末尾）
        String haystack = "1234nbmabc";
        String needle = "abc";
        Solution sl = new Solution();
        int index = sl.strStr(haystack, needle);
        System.out.println(index); // 预期输出: 7

        // 测试用例2：不匹配情况
        System.out.println(sl.strStr("leetcode", "leeto")); // 预期输出: -1

        // 测试用例3：空needle情况（根据Java规范返回0）
        System.out.println(sl.strStr("any", "")); // 预期输出: 0


        /*
        * haystack="1234nbmabc", needle="abc"
        * 1. i=0: 比较'h'vs'a' → 不匹配
        * 2. i=1: 比较'2'vs'a' → 不匹配
        * ...
        * 7. i=7: 比较'a'vs'a', 'b'vs'b', 'c'vs'c' → 完全匹配
        * 返回7
        * */
    }

    /**
     * 暴力匹配算法实现
     * 核心思想：双指针逐字符比较[1,6](@ref)
     * 执行流程：
     * 1. 处理空needle特殊情况
     * 2. 外层循环遍历haystack所有可能起始位置
     * 3. 内层循环验证当前位置是否完全匹配needle
     * 4. 返回首个匹配位置或-1
     *
     * @param haystack 被搜索的主字符串（不应为null）
     * @param needle 要查找的子字符串（可为空）
     * @return 匹配位置的起始索引或-1
     */
    public int strStr(String haystack, String needle) {
        // 获取主串和子串的长度
        int m = haystack.length(); // 主串总长度
        int n = needle.length();   // 子串长度

        /* 处理空子串的特殊情况
         * 根据Java规范，空字符串被视为存在于任何字符串的起始位置
         */
        if (n == 0) {
            return 0;
        }

        /* 外层循环：遍历所有可能的起始位置
         * 关键解释：i <= m - n 的含义
         * 想象你在主串上滑动一个长度为n的窗口：
         * - 当i=0时，窗口覆盖位置0到n-1
         * - 当i=1时，窗口覆盖位置1到n
         * - ...
         * - 最后一个可能的窗口是m-n到m-1
         * 所以i的最大值是m-n，这样窗口才不会超出主串范围
         *
         * 举例说明：
         * haystack="12345" (m=5), needle="23" (n=2)
         * 可能的起始位置i：0,1,2,3 (因为5-2=3)
         * - i=0: 检查"12"
         * - i=1: 检查"23" ← 匹配
         * - i=2: 检查"34"
         * - i=3: 检查"45"
         * 超过i=3就没必要检查了，因为剩余字符不足n=2个
         */
        for (int i = 0; i <= m - n; i++) {
            /* 内层循环：检查从i开始的n个字符是否完全匹配
             * 使用指针j遍历子串的每个字符
             */
            int j;
            for (j = 0; j < n; j++) {
                // 逐个字符比较
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break; // 发现不匹配立即跳出
                }
            }

            /* 检查是否完全匹配
             * 如果j顺利走到n，说明所有字符都匹配了
             */
            if (j == n) {
                return i; // 返回当前起始位置
            }
        }

        // 遍历完所有可能位置都没找到匹配
        return -1;
    }

}

