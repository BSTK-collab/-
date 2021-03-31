package com.nanophase.algorithm.sort;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @since 2021-03-31
 * @apiNote 插入排序 稳定
 * {2, 1, 8, 4, 7, 6, 5} 初始化数组
 * {1, 2, 8, 4, 7, 6, 5} 第一次交换 1,2
 * {1, 2, 4, 8, 7, 6, 5} 第二次交换 4,8
 * {1, 2, 4, 6, 8, 7, 5} 第三次交换 6,7;6,8
 * {1, 2, 4, 6, 7, 8, 5} 第四次交换 7,8
 * {1, 2, 4, 5, 6, 7, 8} 第五次交换 5,8; 5,7; 5,6 交换三次
 *
 * 遍历数组的过程中 如果发现下一个值比这个值小，往前挪移，如果还小，继续挪
 */
public class InsertionSort {

    public static void main(String[] args) {
        for (int index = 0; index < 1000; index++) {
            // 创建随机数组
            int[] randomArray = SortUtil.getRandomArray(1000);
            int[] copyArr = new int[randomArray.length];
            // 拷贝
            System.arraycopy(randomArray,0,copyArr,0,randomArray.length);
            // 调用自定义sort进行排序
            sort(copyArr);
            // 校验排序结果是否正确
           if (!SortUtil.verifyArray(randomArray, copyArr)) {
               System.out.println(false);
           }
        }
    }

    public static void sort(int[] arr) {
        for (int i = 0, j = 1; j < arr.length; i++, j++) {
            while (arr[j] < arr[i]) {
                SortUtil.swap(arr,i,j);
                if (i != 0) {
                    i--;
                }
                if (j > 1) {
                    j--;
                }
            }
        }

        // Arrays.sort()中的插入排序
//        for (int i = 0, j = i; i < arr.length - 1; j = ++i) {
//            int ai = arr[i + 1];
//            while (ai < arr[j]) {
//                arr[j + 1] = arr[j];
//                if (j-- == 0) {
//                    break;
//                }
//            }
//            arr[j + 1] = ai;
//        }
    }
}
