package com.les01;

/**
 * 交替合并两个字符串的解决方案类
 * @author dawnchen
 * @version 1.0
 * @since 2025-03-18
 */

/*
    给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。
    如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
    返回 合并后的字符串 。
*/

public class Solution {

    /**
     * 交替合并两个字符串的核心方法
     * @param word1 第一个输入字符串（非空，由小写字母组成）
     * @param word2 第二个输入字符串（非空，由小写字母组成）
     * @return 合并后的新字符串（交替排列字符，较长字符串的剩余字符追加末尾）
     * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html">Javadoc文档规范</a>
     */
    public String mergeAlternately(String word1, String word2) {
        // 使用StringBuilder提升字符串拼接效率（线程不安全但性能更优）
        StringBuilder sb = new StringBuilder();

        // 双指针初始化：i指向word1起始位置，j指向word2起始位置
        int i = 0, j = 0;

        /*
         * 循环条件说明：
         * 任意一个字符串未遍历完时继续循环
         * 优先处理word1的字符以保持交替顺序
         */
        while (i < word1.length() || j < word2.length()) {
            // 处理word1当前字符（防止数组越界）
            if (i < word1.length()) {
                sb.append(word1.charAt(i++));  // 追加字符后指针自增
            }

            // 处理word2当前字符（与word1交替执行）
            if (j < word2.length()) {
                sb.append(word2.charAt(j++));  // 追加字符后指针自增
            }
        }

        return sb.toString();
    }

    /**
     * 测试用例验证方法（示例）
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        Solution merger = new Solution();

        // 测试示例1：等长字符串
        String result1 = merger.mergeAlternately("abc", "pqr");
        System.out.println(result1);  // 预期输出: apbqcr

        // 测试示例2：word2较长
        String result2 = merger.mergeAlternately("ab", "pqrs");
        System.out.println(result2);  // 预期输出: apbqrs

        // 测试边界条件：空字符串处理
        String result3 = merger.mergeAlternately("a", "");
        System.out.println(result3);  // 预期输出: a
    }
}