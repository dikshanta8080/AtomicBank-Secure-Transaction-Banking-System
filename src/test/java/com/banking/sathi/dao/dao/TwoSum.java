package com.banking.sathi.dao.dao;

public class TwoSum {

    public static void main(String[] args) {
        int target = 10;
        int[] nums = {1, 3, 4, 5, 7, 10};
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int second = nums[j];
                if (first + second == target) {
                    System.out.println(i);
                    System.out.println(j);
                    return;
                }
            }
        }
    }
}
