package com.les02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
    给定两个字符串 s 和 t ，它们只包含小写字母。
    字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
    请找出在 t 中被添加的字母。
*/

public class MP {

    public static void main(String[] args) {
        MP fd = new MP();
        String s = "qivne";
        String t = "eqnigv";
        char result = fd.findTheDifference3(s, t);
        System.out.println(result);
    }


    /**
     * 使用数组统计字符出现次数差异
     * @param s 原字符串（长度n）
     * @param t 添加字符后的字符串（长度n+1）
     * @return 被添加的字符
     * @ref [1][6][7]
     */
    public char findTheDifference1(String s, String t) {
        int[] counts = new int[26]; // 创建26字母的计数器数组
        // 统计s中每个字符出现的次数（加操作）
        for (char c : s.toCharArray()) {
            counts[c - 'a']++; // 将字符映射到数组索引（'a'对应0，'b'对应1...）
        }
        // 遍历t字符串，减少对应字符的计数
        for (char c : t.toCharArray()) {
            counts[c - 'a']--; // 当前字符计数减1
            // 若计数为负，说明该字符在t中多出现一次
            if (counts[c - 'a'] < 0) {
                return c; // 立即返回该字符
            }
        }
        return ' '; // 理论上不会执行（题目保证存在解）
    }


    /**
     * 计算ASCII总和差值确定添加字符
     * @param s 原字符串
     * @param t 添加字符后的字符串
     * @return 差值对应的字符
     * @ref [3][5][8]
     */
    public char findTheDifference2(String s, String t) {
        int sum = 0;
        // 累加t所有字符的ASCII值
        for (char c : t.toCharArray()) {
            sum += c;
        }
        // 减去s所有字符的ASCII值
        for (char c : s.toCharArray()) {
            sum -= c;
        }
        // 差值即为被添加字符的ASCII码
        return (char) sum;
    }

    /**
     * 利用异或运算的归零律找出差异字符
     * @param s 原字符串
     * @param t 添加字符后的字符串
     * @return 异或结果对应的字符
     * @ref [2][3][7][10]
     */
    public char findTheDifference3(String s, String t) {
        char xor = 0;
        // 对s和t所有字符进行异或运算
        for (char c : s.toCharArray()) {
            xor ^= c; // 异或运算：相同字符异或结果为0
        }
        for (char c : t.toCharArray()) {
            xor ^= c; // 最终结果等于被添加字符的ASCII码
        }
        return xor;
    }

    /**
     * 排序后逐字符比对差异
     * @param s 原字符串
     * @param t 添加字符后的字符串
     * @return 第一个不匹配的字符或末尾字符
     * @ref [4][9]
     */
    public char findTheDifference4(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr); // 对s字符数组排序（时间复杂度O(n log n)）
        Arrays.sort(tArr); // 对t字符数组排序

        // 遍历公共长度部分寻找差异
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != tArr[i]) {
                return tArr[i]; // 找到第一个不匹配的字符
            }
        }
        // 若公共部分全匹配，返回t的最后一个字符
        return tArr[tArr.length - 1];
    }

    /**
     * 使用哈希表显式统计字符频率
     * @param s 原字符串
     * @param t 添加字符后的字符串
     * @return 频率不匹配的字符
     * @ref [4][6]
     */
    public char findTheDifference5(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        // 统计s的字符频率（加操作）
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 遍历t字符串，减少对应字符频率
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c) || map.get(c) == 0) {
                return c; // 字符不存在或频率已耗尽
            }
            map.put(c, map.get(c) - 1);
        }
        return ' '; // 理论上不会执行
    }
}
