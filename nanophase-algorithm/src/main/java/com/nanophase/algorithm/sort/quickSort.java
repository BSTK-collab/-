package com.nanophase.algorithm.sort;


import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @since 2021-03-24
 * @apiNote 基本排序算法-快速排序 不稳定
 * 空间复杂度 O(log(n))
 * 时间复杂度 O(n*log(n))
 * 核心思想 随机取数组一个位置的值作为一个基点，一次排序以基点为准，大于该值的放在右边，小于该值的放在左边
 * 递归循环此过程完成排序
 * 一次排序的过程
 * 1，存在一个不为空的数据，随机选中一个基点，假设这里选中了最后一个数为基点;int point = arr[arr.length - 1]
 * 2, 从两边开始遍历 int left = 0; int right = arr.length - 1;
 * 3, left开始向前遍历，如果arr[left] > point; swap arr[left],arr[right]
 * 4, right向左边遍历，如果arr[right] < point; swap arr[left],arr[right]
 */
public class quickSort {

    public static void main(String[] args) {
        int[] randomArray = SortUtil.getRandomArray(10);
        SortUtil.print(randomArray);
        System.out.println();
        System.out.println("===================================");
        sort(randomArray);
    }

    // TODO: 2021/3/25 一次排序 默认按照从小到大排序
    public static void sort(int[] arr) {
        int[] sortArray = new int[arr.length];
        // 1，数据源；2，起始位置；3，目标数组；4，截止位置
        System.arraycopy(arr, 0, sortArray, 0, arr.length);

        // 双指针校验，头尾遍历
        // 获取基点，小于该基点的值放左边，反之右边
        int pointSite = sortArray.length - 1;
        int point = sortArray[pointSite];
        // 左指针
        int left = 0;
        // 右指针
        int right = sortArray.length - 1;
        // TODO: 2021/3/25 先只考虑偶数情况
        while (left < right) {

            // 使用if判断会有问题 需要while循环找到索引位置 然后跳出while循环进行位置交换
            // arr[0] = 1000 > 20 ;0 > arr[20],10 < arr[20]; 0 <==> 10,
            while (left < right && sortArray[left] < point) {
                left++;
            }
            while (left < right && sortArray[right] > point) {
                right--;
            }
            SortUtil.swap(sortArray,left,right);
            SortUtil.swap(sortArray,right,pointSite);
            // 左边的大于基点
//            if (sortArray[left] > point) {
//                sortUtil.swap(sortArray,left,pointSite);
//                pointSite = left;
//            }
//            if (sortArray[right] < point) {
//                // 这样交换只是i和j交换 没有起到排到point之后的作用
//                sortUtil.swap(sortArray,right,pointSite);
//                pointSite = right;
//            }
//            left++;
//            right--;
        }
        SortUtil.print(sortArray);
    }
}
