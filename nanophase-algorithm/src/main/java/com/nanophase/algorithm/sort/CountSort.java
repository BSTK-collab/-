package com.nanophase.algorithm.sort;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @date 2021-04-06
 * @apiNote 计数排序
 * 空间复杂度 O(N + k); k为整数的范围; 稳定
 * 计数排序适用于数组长度较大，但是数值范围较小的场景。
 * 比如计算某大型公司数万名员工年龄的问题，假设年龄范围设置在0-150之间
 * 其思想在于创建一个长度为150的数组，分别存储0-150的数字。然后在遍历全部员工的时候，计算重复的年龄数量
 * 比如20岁员工数量 = x，将x值添加至索引为20的位置
 * 再假设30岁员工数量 = y，将y的值添加至索引为30的位置，只计算每个数字在数组中出现的次数
 * 最后创建一个与要排序的数组同等长度的新数组，直接赋值即可
 */
public class CountSort {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] randomArray = SortUtil.getRandomArray(10000);
            int[] arr = new int[randomArray.length];
            System.arraycopy(randomArray, 0, arr, 0, arr.length);
            arr = sort(arr);
            if (!SortUtil.verifyArray(randomArray, arr)) {
                System.out.println(false);
            }
        }
    }

    public static int[] sort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        return sort(arr, 0, arr.length);
    }

    /**
     * 普通计数排序 优化 稳定，解决取值范围数据浪费空间问题
     *
     * @param arr   要排序的数组
     * @param left  左起始位置
     * @param right 截止位置
     * @return 排好序的新数组
     */
    public static int[] sort(int[] arr, int left, int right) {
        if (arr.length < 2 || right - left < 2) {
            return arr;
        }
        // 创建新数组 保存排序完毕的值
        int[] result = new int[right - left];
        // 获取数组的最大值和最小值
        int[] maxAndMinValue = SortUtil.findMaxAndMinValue(arr, left, right);
        // 最小值
        int minValue = maxAndMinValue[1];
        // 创建合适大小的计数数组
        int[] countArr = new int[maxAndMinValue[0] - minValue + 1];
        // 根据minVal初始化计数数组
        for (int i = left; i < right; i++) {
            // 原值 - minValue = 计数数组的索引位置; +1 是计算计数数组索引位置保存的值(相同数字出现的次数)
            countArr[arr[i] - minValue] += 1;
        }

        /*
         * 如何保证计数排序的稳定性？
         * 边界限定：首先除了计数数组外不应创建新的数组，否则空间复杂度不满足O(n + k)原则
         * 计数数组的值都是通过原数组和标的值(minVal)所计算出来的，计数数组可以根据标的值 倒推出原数组的值所在的索引位置
         * 首先可以确定countArr的所有值相加 = arr.length;
         * 假设这个数组是计数数组：int[] countArr = {2  3  1  4  3  2  2  3  2  1  3}
         * 对应的                        index = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
         * 假设取到的原数组值arr[i] = 5；最终赋值形式为result[j] = arr[i];问题转换到j的计算上来
         * 可以根据这个取到的值(5)查询countArr(计数数组),获取到对应的值为2(出现次数)
         * 那么落点位置 j = 2 + 3 + 1 + 4 + 3 + 2 = 15 - 1 = 14的位置(countArr数组索引5之前所有值的和)
         * 减一是为了避免数组越界
         * */
        // 转换计数数组形式，将所存储的值的具体出现次数转换为值在result中的落点位置
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }

        // 将具体的值根据具体落点位置赋值给result数组
        for (int i = left; i < right; i++) {
            // 在这里发生数组越界不会抛出异常
            result[--countArr[arr[i] - minValue]] = arr[i];
        }
        return result;
    }

    /**
     * 山寨版计数排序 不稳定 排序数组取值范围 0 - 10
     *
     * @param arr   要排序的数组
     * @param left  左起始位置
     * @param right 截止位置
     * @return
     */
    public static int[] sort1(int[] arr, int left, int right) {
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