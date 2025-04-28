package com.les01;

import java.util.Arrays;

/**
 * 交替合并两个字符串的解决方案类
 * 实现功能：将两个字符串的字符按交替顺序合并，较长字符串的剩余字符追加末尾
 * 算法特性：时间复杂度O(n)，空间复杂度O(n)（结果字符串空间）
 * @author dawnchen
 * @version 1.0
 * @since 2025-03-18
 */
public class Solution {

    /**
     * 核心方法：交替合并两个字符串
     * 实现思路：
     * 1. 使用双指针技术同步遍历两个字符串
     * 2. 通过StringBuilder高效构建结果字符串
     * 3. 处理不等长字符串的剩余字符
     *
     * @param word1 第一个输入字符串（必须非空，仅含小写字母）
     * @param word2 第二个输入字符串（必须非空，仅含小写字母）
     * @return 合并后的新字符串（交替排列，剩余字符追加末尾）
     */
    public String mergeAlternately(String word1, String word2) {
        // 使用StringBuilder而非String直接拼接：
        // 1. 避免每次拼接创建新String对象
        // 2. 线程不安全但在方法局部变量中使用是安全的
        // 3. 相比StringBuffer有约10-15%的性能提升[6,8](@ref)
        StringBuilder sb = new StringBuilder();

        // 双指针初始化：
        // i - word1的当前索引（从0开始）
        // j - word2的当前索引（从0开始）
        int i = 0, j = 0;

        /*
         * 循环控制逻辑：
         * 1. 使用OR条件确保遍历完较长的字符串
         * 2. 每次循环先处理word1再处理word2，保证交替顺序
         * 3. 通过指针自增实现遍历移动
         */
        while (i < word1.length() || j < word2.length()) {
            // 处理word1当前字符（如果尚未遍历完）
            if (i < word1.length()) {
                // 追加字符并移动指针：
                // 1. charAt()获取指定位置字符（O(1)时间复杂度）
                // 2. 使用后置自增(i++)在追加后自动移动指针
                sb.append(word1.charAt(i++));
            }

            // 处理word2当前字符（如果尚未遍历完）
            if (j < word2.length()) {
                // 与word1相同的处理逻辑，但保持交替顺序：
                // 例如word1先追加第0位，然后word2追加第0位
                sb.append(word2.charAt(j++));
            }
            /*
             * 示例执行流程（输入"ab","pqrs"）：
             * 第1轮循环：i=0,j=0 -> 追加'a'，追加'p' -> sb="ap"
             * 第2轮循环：i=1,j=1 -> 追加'b'，追加'q' -> sb="apbq"
             * 第3轮循环：i=2(越界),j=2 -> 跳过word1，追加'r' -> sb="apbqr"
             * 第4轮循环：i=2,j=3 -> 跳过word1，追加's' -> sb="apbqrs"
             */
        }

        // 将StringBuilder转换为不可变String返回
        return sb.toString();
    }

    /**
     * 测试方法：验证核心功能
     * 包含三类测试用例：
     * 1. 等长字符串合并
     * 2. 不等长字符串合并
     * 3. 边界条件（空字符串）处理
     */
    public static void main(String[] args) {
        Solution merger = new Solution();

        // 测试用例1：等长字符串
        // 预期：a,p,b,q,c,r -> "apbqcr"
        String result1 = merger.mergeAlternately("abc", "pqr");
        System.out.println("测试1结果: " + result1);

        // 测试用例2：word2较长
        // 预期：a,p,b,q, (r,s追加) -> "apbqrs"
        String result2 = merger.mergeAlternately("ab", "pqrs");
        System.out.println("测试2结果: " + result2);

        // 测试用例3：边界条件（空字符串）
        // 预期：直接返回非空字符串"a"
        String result3 = merger.mergeAlternately("a", "");
        System.out.println("测试3结果: " + result3);
    }
}