package com.les05;

/**
 * 该类提供检测字符串是否由重复子串构成的功能
 *
 * 算法思路：
 * 1. 遍历所有可能的子串长度（从1到n/2）
 * 2. 对于每个可能的子串长度i，检查字符串是否能被i整除
 * 3. 如果能整除，则检查字符串是否由该子串重复构成
 * 4. 如果找到符合条件的子串，立即返回true
 * 5. 如果所有可能性都检查完毕仍未找到，返回false
 *
 * 时间复杂度：O(n^2)，其中n是字符串长度
 * 空间复杂度：O(1)，仅使用常数空间
 */
public class Solution {

    /**
     * 主方法：程序入口，用于测试repeatedSubstringPattern方法
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        // 创建Solution实例，以便调用非静态方法
        Solution sl = new Solution();

        // 测试字符串"abcabcbb"是否能由子串重复构成
        // 预期结果：false，因为"abcabcbb"不能由某个子串完全重复构成
        boolean flag = sl.repeatedSubstringPattern("abcabcbb");

        // 打印检测结果
        System.out.println(flag);
    }

    /**
     * 检测字符串是否可由子串重复多次构成
     *
     * 核心算法说明：
     * 该方法通过检查字符串是否能被某个子串重复构成来判断。
     * 例如："abcabcabc"可以由"abc"重复3次构成
     *
             * @param s 待检测的字符串
     * @return true-可重复构成 false-不可重复构成
     */
    public boolean repeatedSubstringPattern(String s) {
        // 获取字符串长度
        int n = s.length();
        
        /**
         * 遍历所有可能的子串长度i（从1到n/2）
         * 为什么只遍历到n/2？
         * - 因为子串至少要重复2次才能构成整个字符串
         * - 所以子串长度最大只能是n/2
         * - 例如：对于长度6的字符串，最多检查长度为3的子串（需要重复2次）
         */
        for (int i = 1; i * 2 <= n; ++i) {
            
            /**
             * 检查字符串长度n是否能被子串长度i整除（字符串长度n必须能被候选子串长度i整除，通过整除性排除不可能的子串长度）
             * 为什么需要这个条件？
             * - 如果n不能被i整除，那么字符串不可能由长度为i的子串重复构成
             * - 例如：长度为7的字符串不能被长度为3的子串整除（7%3=1≠0）
             * - 只有能整除时，才可能有k=n/i次重复
             */
            if (n % i == 0) {
                // 初始化匹配标志为true
                boolean match = true;
                
                /**
                 * 检查字符串是否确实由长度为i的子串重复构成
                 * 从第i个字符开始比较（因为前i个字符就是候选子串）
                 * 比较每个字符与它前面i个位置的字符是否相同
                 *
                 * 例如：对于"ababab"和i=2
                 * - j=2: s[2]('a')应该等于s[0]('a')
                 * - j=3: s[3]('b')应该等于s[1]('b')
                 * - j=4: s[4]('a')应该等于s[2]('a')
                 * - j=5: s[5]('b')应该等于s[3]('b')
                 */
                for (int j = i; j < n; ++j) {
                    // 比较当前字符与子串对应位置的字符
                    if (s.charAt(j) != s.charAt(j - i)) {
                        // 发现不匹配，标记为false并跳出循环
                        match = false;
                        break;
                    }
                }

                // 如果所有位置都匹配，说明找到符合条件的子串
                if (match) {
                    return true;
                }
            }
        }

        // 遍历完所有可能的子串长度后仍未找到匹配，返回false
        return false;
    }
}