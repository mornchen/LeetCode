package com.les06;

import java.util.Arrays;

/**
 * 该类提供将数组中所有零移动到末尾的功能，同时保持非零元素的原始顺序
 * 算法核心：双指针技术（快慢指针）
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Solution {

    /**
     * 程序入口，用于测试moveZeroes方法
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        Solution sl = new Solution();
        // 测试数组包含多个零和非零元素
        int[] nums = {8, 3, 5, 2, 2, 0, 0, 0, 1, 0, 3, 12};
        sl.moveZeroes(nums);
        // 打印处理后的数组
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 移动零元素到数组末尾的核心方法
     * 算法思路：
     * 1. 使用双指针技术，k指针指向下一个非零元素应该存放的位置
     * 2. i指针遍历整个数组寻找非零元素
     * 3. 当找到非零元素时，将其与k指针位置交换
     * 4. 保证所有非零元素的相对顺序不变
     *
     * @param nums 待处理的整型数组（原地修改）
     */
    public void moveZeroes(int[] nums) {
        // k指针：记录下一个非零元素应该放置的位置
        // 初始为0，表示从数组头部开始填充非零元素
        int k = 0;

        // i指针：遍历整个数组的索引
        for (int i = 0; i < nums.length; i++) {
            // 当发现非零元素时进行处理
            if (nums[i] != 0) {
                /**
                 * 避免不必要的自身交换：
                 * 当i == k时说明不需要交换，直接移动指针即可
                 * 这个判断可以优化性能，减少不必要的赋值操作
                 */
                if (i != k) {
                    // 交换非零元素到k位置
                    int temp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = temp;
                }
                // 若k当前的位置已经有非零值或已经填充非零值，则移动k指针到下一个待填充位置
                k++;
            }
            /**
             * 当nums[i] == 0时：
             * i指针继续前进，k指针保持不动
             * 这样k指针始终指向第一个零元素的位置（如果有的话）
             */
        }
    }
}