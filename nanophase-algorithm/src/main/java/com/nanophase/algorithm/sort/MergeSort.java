package com.nanophase.algorithm.sort;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @since 2021-03-30
 * @apiNote 归并排序
 * 时间复杂度 O(nlog(n))
 * 空间复杂度O(nlog(n))
 * 稳定
 * 假定存在一个乱序数组，数组取半，左数组起始位置为i，右数组起始位置为j
 * 创建一个相同大小的新数组
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{1, 4, 7, 8, 3, 6, 9};
        int[] arr = SortUtil.getRandomArray(5);
        System.out.println("排序前的数组");
        SortUtil.print(arr);
        sort(arr);
    }

    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        arr = new int[]{1, 4, 7, 8, 3, 6, 9};
        sort(arr,0,arr.length);
    }

    // 一次归并排序
    public static void sort(int[] arr,int left,int rightBound) {
        if (arr.length <= 1 || left >= rightBound) {
            return;
        }
        int[] a = new int[rightBound - left];
        // 左数组起始位置
        int i = left;
        // 左数组截止位置
        int mid = rightBound >> 1;
        // 右数组的起始位置
        int j = mid + 1;
        // 新数组的起始位置
        int k = 0;
        while (i <= mid && j < rightBound) {
            a[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            a[k++] = arr[i++];
        }
        while (j < rightBound) {
            a[k++] = arr[j++];
        }

        System.out.println();
        System.out.println("排序后的数组");
        SortUtil.print(a);
    }
}
