package com.nanophase.algorithm.sort;


import com.nanophase.algorithm.common.SortUtil;

import java.util.Arrays;

/**
 * @author zhj
 * @apiNote 基本排序算法-快速排序 不稳定
 * 空间复杂度 O(log(n))
 * 时间复杂度 O(n*log(n))
 * 核心思想 随机取数组一个位置的值作为一个基点，一次排序以基点为准，大于该值的放在右边，小于该值的放在左边
 * 递归循环此过程完成排序
 * @since 2021-03-24
 */
public class quickSort {

    public static void main(String[] args) {
        // create random array
//        int[] randomArray = SortUtil.getRandomArray(10000);
//        int[] arr = new int[randomArray.length];
//        System.arraycopy(randomArray,0,arr,0,randomArray.length);
//        sort(randomArray);
//        // 使用对数器校验排序是否合格
//        System.out.println(SortUtil.verifyArray(arr, randomArray));

        int[] arr = SortUtil.getRandomArray(10);
        Tsort(arr,0,arr.length - 1);
//        SortUtil.print(arr);
    }

    public static void Tsort(int[] arr, int left,int right) {
        if (arr.length <= 1) {
            return;
        }
//        System.out.println("排序前的数组");
//        SortUtil.print(arr);
//        System.out.println();
//        sort3(arr, 0, arr.length - 1);
//        arr = new int[]{1614873282, 2068072312, -655861042, -863478095, -2077691062, -1424334473, 1898156318, -1195214949, -1497631752, -1269145485};
        if (left <= right) {
            int sort = sort(arr, 0, arr.length - 1);
            sort(arr,left,sort - 1);
            sort(arr,sort + 1,right);
        }
//        sort1(arr);
        System.out.println("排序后的数组");
        SortUtil.print(arr);
    }

    /**
     * 百度版
     * @param arr
     * @param left
     * @param right
     */
    public static void sort3(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int i = left;
        int j = right;
        int pivot = arr[right];
        while (i < j) {
            while (i < j && arr[i] < pivot) {
                i++;
            }
            while (i < j && arr[j] > pivot) {
                j--;
            }
            if (arr[i] == arr[j] && i < j) {
                i++;
            } else {
                SortUtil.swap(arr, i, j);
            }
        }
        if (i - 1 > left) sort3(arr, left, i - 1);
        if (j + 1 < right) sort3(arr, j + 1, right);
    }

    /**
     * quickSort写法不一，这是一种写法 // TODO: 2021/3/26 有bug
     *
     * @param arr   要排序的数组
     * @param left  左指针
     * @param right 右指针
     * @return
     */
    public static int sort(int[] arr, int left, int right) {
//        if (left >= right || right - left == 1) {
//            return;
//        }
        // 基点值
        int pivot = arr[right];
        System.out.println("基点值" + pivot);
        // 左指针
        int i = left;
        // 右指针
        int j = right - 1;

        while (i < j) {
            // 左指针递增 找到最近的大于基点的值的位置
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            // 右指针递减 找到最近的小于等于基点的值的位置
            while (i < j && arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                // 将找到的大小值进行交换
                SortUtil.swap(arr, i, j);
            }
            System.out.println("i>>>>" + i + ";j----->" + j);
        }
        // 最后 交换基点的值
        if (j >= 0 && arr[j] > pivot) {
            SortUtil.swap(arr, j, right);
        }
        return i;
//        sort(arr, left, i - 1);
//        sort(arr, i, right);
    }

    // 一次排序示例 将小于基点的值放左边 反之右边
    public static void sort1(int[] arr) {
        int[] sortArray = new int[arr.length];
        // 拷贝数组；入参:1，数据源；2，起始位置；3，目标数组；4，截止位置
        // quickSort的空间损耗不应该创建新数组;该操作不计入算法的空间损耗 只是为了和Arrays.sort(arr)的结果进行比较
        System.arraycopy(arr, 0, sortArray, 0, arr.length);
        sortArray = new int[]{-325978909, -1090165760, -673624493, -1537328321, -1082194256, -355563631, -1683773164, 2143272252, 1737262753, 1095592451};//5<==>4,7<==>4,10,j条件不成立
//        中间的交换逻辑
//        2, 5, 3, 7, 10, 6, 8, 0, 4, 0, 1, 9, 11, 2, 0, 2 初始数组 基点值 = 2；right值 = 0
//        2, 0, 3, 7, 10, 6, 8, 0, 4, 0, 1, 9, 11, 2, 5, 2 第一次交换
//        2, 0, 2, 7, 10, 6, 8, 0, 4, 0, 1, 9, 11, 3, 5, 2 第二次交换
//        2, 0, 2, 1, 10, 6, 8, 0, 4, 0, 7, 9, 11, 3, 5, 2 第三次交换
//        2, 0, 2, 1, 0, 6, 8, 0, 4, 10, 7, 9, 11, 3, 5, 2 第四次交换
//        2, 0, 2, 1, 0, 0, 8, 6, 4, 10, 7, 9, 11, 3, 5, 2 第五次交换
//        2, 0, 2, 1, 0, 0, 2, 6, 4, 10, 7, 9, 11, 3, 5, 8 最终结果

        // 双指针校验，头尾遍历
        // 获取基点，小于该基点的值放左边，反之右边
        int pointSite = sortArray.length - 1;
        int point = sortArray[pointSite];
        // 左指针
        int left = 0;
        // 右指针 由于选中的基点为最右边的数 可以从arr[arr.length - 1 - 1]开始递减
        int right = pointSite - 1;
        while (left < right) {

            // 使用if判断会有问题 需要while循环找到索引位置 然后跳出while循环进行位置交换
            // arr[0] = 1000 > 20 ;0 > arr[20],10 < arr[20]; 0 <==> 10,
            // 如果左值小于等于基点 则一直右移，直到找到大于基点的值所在的位置；=时可以不移动
            while (left < right && sortArray[left] <= point) {
                left++;
            }
            // 如果右值大于基点，则一直左移，直到找到小于基点的值所在的位置
            while (left < right && sortArray[right] > point) {
                right--;
            }
            // left == right时 双指针指在了同一个位置，可以不做交换(该判断不影响整体算法性能，只是逻辑需要，不加也可)
            if (left != right) {
                SortUtil.swap(sortArray, left, right);
                System.out.println("交换结果");
                SortUtil.print(sortArray);
            }
        }
        if (sortArray[right] > point) {
            SortUtil.swap(sortArray, right, pointSite);
        }
        System.out.println("最终结果");
        SortUtil.print(sortArray);
    }

    // TODO: 2021/3/26 这个算法排序不准确
    public static int sort2(int[] arr, int left, int right) {
        int point = arr[right];//基点的值
        int i = left;//左开始处
        int j = right;//右开始处
        int k = right;//基点的位置可能会改变 记录基点的索引位置
        //先将小于等于基	点的值放在左边，反之右边
        while (i < j) {
//            i走过的路j无需重复走
            while (i < j && arr[i] <= point) {
                i++;//找到了大于基点的 索引位置
            }
//            i走过的路j无需重复走
            while (i < j && arr[j] > point) {
                j--;//找到了小于基点的 索引位置
            }
            //交换位置
            SortUtil.swap(arr, i, j);
            SortUtil.swap(arr, j, k);
            k = j;
        }
        SortUtil.print(arr);
        return i;
    }
}
