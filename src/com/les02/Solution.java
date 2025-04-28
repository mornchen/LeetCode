package com.les02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 解决字符串差异字符查找问题的工具类
 * 提供5种不同算法实现，适用于不同场景需求
 * 典型应用场景：版本控制、数据校验等需要检测单字符差异的场景
 */
public class Solution {

    /**
     * 测试入口
     * 示例输入：s="qivne", t="eqnigv"（t比s多'g'）
     */
    public static void main(String[] args) {
        Solution fd = new Solution();
        String s = "qivne";
        String t = "eqnigv";
        char result = fd.findTheDifference3(s, t); // 使用异或法检测
        System.out.println(result); // 预期输出：g
    }

    /**
     * 方法1：字符计数法
     * 核心思想：通过26位数组统计字符频率差异
     * 时间复杂度：O(n) 空间复杂度：O(1)（固定26长度数组）
     * 执行流程：
     * 1. 初始化字母表计数器
     * 2. 遍历s字符串进行字符计数（++操作）
     * 3. 遍历t字符串进行字符抵消（--操作）
     * 4. 首次出现负计数字符即为结果
     */
    public char findTheDifference1(String s, String t) {
        // 创建26字母计数器（小写字母ASCII连续特性）
        int[] counts = new int[26];

        // 正向计数阶段：统计s中各字符出现次数
        for (char c : s.toCharArray()) {
            counts[c - 'a']++; // 'a'->0, 'b'->1,...'z'->25
        }

        // 逆向抵消阶段：检测t中的多余字符
        for (char c : t.toCharArray()) {
            counts[c - 'a']--;
            if (counts[c - 'a'] < 0) { // 首次出现负值
                return c; // 立即返回当前字符
            }
        }
        return ' '; // 理论上不会执行（题目保证有解）
    }

    /**
     * 方法2：ASCII差值法
     * 核心思想：利用数学求和差确定差异字符
     * 时间复杂度：O(n) 空间复杂度：O(1)
     * 执行流程：
     * 1. 计算t所有字符ASCII总和
     * 2. 减去s所有字符ASCII总和
     * 3. 差值直接转换为目标字符
     */
    public char findTheDifference2(String s, String t) {
        int sum = 0;
        // 阶段1：累加t的ASCII总和
        for (char c : t.toCharArray()) {
            sum += c; // 隐式类型转换char->int
        }
        // 阶段2：减去s的ASCII总和
        for (char c : s.toCharArray()) {
            sum -= c;
        }
        // 差值即为目标字符的ASCII码
        return (char) sum; // 显式类型转换int->char
    }

    /**
     * 方法3：异或位运算法
     * 核心思想：利用异或的归零律（a^a=0）和恒等律（a^0=a）
     * 时间复杂度：O(n) 空间复杂度：O(1)
     * 执行流程：
     * 1. 初始化异或结果为0
     * 2. 对s所有字符执行异或
     * 3. 对t所有字符执行异或
     * 4. 最终结果即为差异字符
     */
    public char findTheDifference3(String s, String t) {
        char xor = 0;
        // 阶段1：异或s所有字符
        for (char c : s.toCharArray()) {
            xor ^= c; // 相同字符异或会抵消
        }
        // 阶段2：异或t所有字符
        for (char c : t.toCharArray()) {
            xor ^= c; // 最终保留差异字符
        }
        return xor; // 自动返回char类型
    }

    /**
     * 方法4：排序比对法
     * 核心思想：排序后线性扫描找差异
     * 时间复杂度：O(n log n) 空间复杂度：O(n)
     * 执行流程：
     * 1. 将s和t转为字符数组
     * 2. 对两个数组分别排序
     * 3. 遍历比较对应位置字符
     * 4. 发现差异或返回末尾字符
     */
    public char findTheDifference4(String s, String t) {
        // 转换为字符数组（需要额外空间）
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        // 排序阶段（双O(n log n)操作）
        Arrays.sort(sArr);
        Arrays.sort(tArr);

        // 线性扫描阶段
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != tArr[i]) {
                return tArr[i]; // 发现位置差异
            }
        }
        // 全匹配时返回t的最后字符
        return tArr[tArr.length - 1];
    }

    /**
     * 方法5：哈希表统计法
     * 核心思想：显式使用HashMap统计频率差异
     * 时间复杂度：O(n) 空间复杂度：O(n)
     * 执行流程：
     * 1. 初始化HashMap
     * 2. 统计s字符频率（put操作）
     * 3. 遍历t字符进行抵消（get操作）
     * 4. 发现零频或不存在字符
     */
    public char findTheDifference5(String s, String t) {
        // 初始化字符频率表
        Map<Character, Integer> map = new HashMap<>();

        // 频率统计阶段
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // 频率检测阶段
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c) || map.get(c) == 0) {
                return c; // 发现新字符或频率耗尽
            }
            map.put(c, map.get(c) - 1); // 频率递减
        }
        return ' '; // 保底返回
    }
}

/*
* 典型执行示例（以异或法为例）
* 输入：s="abc", t="cbda"
* 初始化xor=0
* 异或s字符：0^'a'^'b'^'c'=0x61^0x62^0x63=0x60
* 异或t字符：0x60^'c'^'b'^'d'^'a'=0x64（'d'的ASCII）
* 返回'd'
* */

