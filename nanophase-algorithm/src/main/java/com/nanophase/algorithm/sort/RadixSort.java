package com.nanophase.algorithm.sort;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @date 2021-04-08
 * @apiNote 基数排序 稳定 非比较型整数排序方法
 * 以计数排序和桶思想为基础，取数组中 位数最长的数字，其余数字位数不足者补足0
 * 有两种排序方式，一种从低位开始，逐步递增，一步步排序，最终到高位;反之高位开始
 * 这里使用的低位开始，将整数切割成位数后，按照位数进行一次排序。由低位一直排到高位到有序数列为止
 * <p>
 * int n = 21312; 倒序输出
 * System.out.println(n % 10);// 2
 * System.out.println(n / 10 % 10); // 1
 * System.out.println(n / 100 % 10); // 3
 * System.out.println(n / 1000 % 10); // 1
 * System.out.println(n / 10000 % 10); // 2
 */
public class RadixSort {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] randomArray = SortUtil.getRandomArray(1000);
            int[] arr = new int[randomArray.length];
            System.arraycopy(randomArray,0,arr,0,arr.length);
            sort(arr);
            if (!SortUtil.verifyArray(randomArray,arr)) {
                System.out.println("false");
            }
        }
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
        int maxVal = arr[left];
        for (int i = left + 1; i < right; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }
        sort(arr, maxVal, left, right);
    }

    /**
     * 排序主逻辑
     *
     * @param arr     排序数组
     * @param maxVal 数组的最大值
     * @param left   左指针
     * @param right  右指针
     */
    private static void sort(int[] arr, int maxVal, int left, int right) {
        // 使用计数排序针对每一位 位数对原数组进行排序
        for (int i = 1; maxVal / i > 0; i *= 10) {
            // 循环调用计数排序
            countSort(arr, i, left, right);
        }
    }

    /**
     * 计数排序
     *
     * @param arr   排序数组
     * @param exp   除数
     * @param left  左指针
     * @param right 右指针
     */
    private static void countSort(int[] arr, int exp, int left, int right) {
        int[] result = new int[right - left];
        // 创建计数数组 此处的计数数组长度最大为10，用于保存位数的次数
        int[] countArr = new int[10];
        // 初始化计数数组
        for (int i = left; i < right; i++) {
            // 从原值中取出位数 并记录次数存放在计数数组中
            countArr[arr[i] / exp % 10] += 1;
        }

        // 转换计数数组形式，协助计算原值的落点位置
        for (int i = 1; i < 10; i++) {
            countArr[i] += countArr[i - 1];
        }

        // 进行排序 这里必须要倒序遍历
        for (int i = right - 1; i >= left; i--) {
            result[--countArr[arr[i] / exp % 10]] = arr[i];
        }

        // 排好序后写回原数组
        if (right - left >= 0){
            System.arraycopy(result, left, arr, left, right - left);
        }
    }
}
