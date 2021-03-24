package com.nanophase.algorithm.sort;


/**
 * @author zhj
 * @since 2021-03-24
 * @apiNote 基本排序算法-快速排序 不稳定
 * 时间复杂度 O(n*log(n))
 * 空间复杂度 O(log(n))
 */
public class quickSort {

    public static void main(String[] args) {

    }

    public int[] sort(int[] arr){
        int[] sortArray = new int[arr.length];
        // 1，数据源；2，起始位置；3，目标数组；4，截止位置
        System.arraycopy(arr,0,sortArray,0,arr.length);

        // 获取基点，小于该基点的值放左边，反之右边
        int point = sortArray[sortArray.length - 1];
        return arr;
    }
}
