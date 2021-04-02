package com.nanophase.algorithm.sort;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @date 2021-04-02
 * @apiNote 冒泡排序 两两比较 稳定
 * 时间复杂度 O(n²)
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 7, 8, 3, 6, 9, 0, 8, 4};
        sort(arr);
        SortUtil.print(arr);
    }

    public static void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        sort(arr, 0, arr.length);
    }

    public static void sort(int[] arr, int left, int right) {
        if (arr.length < 2 || right - left < 2) {
            return;
        }
        for (int i = left; i < right; i++) {
            for (int j = left; j < right - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortUtil.swap(arr, j, j + 1);
                }
            }
        }
    }
}