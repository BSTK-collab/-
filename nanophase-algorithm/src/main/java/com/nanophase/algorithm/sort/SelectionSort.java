package com.nanophase.algorithm.sort;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @date 2021-04-02
 * @apiNote 选择排序 使用较少，排序不稳定，时间复杂度O(n²)
 * 选择排序的思想，给定一组长度大于1的乱序数组，遍历数组获取到最小值，交换到首位
 * 然后从剩下的数组中获取最小值交换到第二个位置，以此类推，交换至末尾完成排序
 * 优化：每次找到最小值时，同时也可以找到最大值
 */
public class SelectionSort {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] arr = SortUtil.getRandomArray(1000);
            int[] copyArr = new int[arr.length];
            System.arraycopy(arr, 0, copyArr, 0, arr.length);
            sort(copyArr);
            if (!SortUtil.verifyArray(arr, copyArr)) {
                System.out.println(false);
            }
        }
    }

    public static void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        sort(arr, 0, arr.length);
    }

    /**
     * 选择排序主方法(优化，每次循环找到最小值时，同时也找到最大值)
     *
     * @param arr   排序数组
     * @param left  要排序的起始位置
     * @param right 要排序的截止位置
     */
    public static void sort(int[] arr, int left, int right) {
        if (arr.length < 2 || right - left < 2) {
            return;
        }
        for (int i = left, k = right - 1; i < k; i++, k--) {
            int min = i;
            int max = k;
            for (int j = i; j <= k; j++) {
                // 获取最小值位置
                if (arr[j] < arr[min]) {
                    min = j;
                }
                // 获取最大值位置
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }
            // 有可能第一个值就是最大值
            if (arr[i] == arr[max]) {
                // 移动最大值位置到末尾
                SortUtil.swap(arr, i, k);
                // 最大值交换后可能最小值的位置被改变
                if (min != k) {
                    // 移动最小值到首位
                    SortUtil.swap(arr, i, min);
                }
                continue;
            }
            if (arr[min] < arr[i]) {
                SortUtil.swap(arr, i, min);
            }
            if (arr[max] > arr[k]) {
                SortUtil.swap(arr, k, max);
            }
        }
    }

    /**
     * 选择排序主方法 (未优化版 每次只找到一个最小值)
     *
     * @param arr   排序数组
     * @param left  要排序的起始位置
     * @param right 要排序的截止位置
     */
    public static void sort1(int[] arr, int left, int right) {
        if (arr.length < 2 || right - left < 2) {
            return;
        }
        for (int i = left; i < right - 1; i++) {
            for (int j = i + 1; j < right; j++) {
                if (arr[j] < arr[i]) {
                    SortUtil.swap(arr, i, j);
                }
            }
        }
    }
}
