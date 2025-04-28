package com.les06;

import java.util.Arrays;

public class demo {

    public static void main(String[] args) {
        Solution sl = new Solution();
        int[] nums = {8, 3, 5, 2, 2, 0, 0, 0, 1, 0, 3, 12};
        sl.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeroes(int[] nums) {
        int j = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }

                j++;
            }
        }

    }
}
