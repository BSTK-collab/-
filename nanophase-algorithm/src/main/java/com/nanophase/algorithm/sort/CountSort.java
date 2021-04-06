package com.nanophase.algorithm.sort;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @date 2021-04-06
 * @apiNote 计数排序
 * 空间复杂度 O(N) + k
 * 计数排序适用于数组长度较大，但是数值范围较小的场景。
 * 比如计算某大型公司数万名员工年龄的问题，假设年龄范围设置在0-150之间
 * 其思想在于创建一个长度为150的数组，分别存储0-150的数字。然后在遍历全部员工的时候，计算重复的年龄数量
 * 比如20岁员工数量 = x，将x值添加至索引为20的位置
 * 再假设30岁员工数量 = y，将y的值添加至索引为30的位置，只计算每个数字在数组中出现的次数
 * 最后创建一个与要排序的数组同等长度的新数组，直接赋值即可
 */
public class CountSort {
    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 6, 1, 1, 1, 10, 3, 8, 8, 9, 0, 5, 7, 7, 7, 6, 2, 0, 0, 4, 4, 4, 10, 10};
        arr = sort(arr);
        SortUtil.print(arr);
    }

    public static int[] sort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        return sort(arr, 0, arr.length);
    }

    public static int[] sort(int[] arr, int left, int right) {
        if (arr.length < 2 || right - left < 2) {
            return arr;
        }
        // 创建与要排序数组的取值范围相匹配的数组
        int[] dataRange = new int[11];
        // 创建新数组 保存排序完毕的值
        int[] result = new int[right - left];

        // 进行计数
        for (int i = left; i < right; i++) {
            int value = arr[i];
            dataRange[value] = dataRange[value] == 0 ? 1 : ++dataRange[value];
        }
        // 计数完毕，写回原数组
        return writeResult(result, dataRange, left, right);
    }

    private static int[] writeResult(int[] result, int[] dataRange, int left, int right) {
        for (int i = 0, k = left; i < dataRange.length; i++) {
            int value = dataRange[i];
            if (value != 0 && k < right) {
                for (int j = 0; j < value; j++) {
                    result[k++] = i;
                }
            }
        }
        return result;
    }
}