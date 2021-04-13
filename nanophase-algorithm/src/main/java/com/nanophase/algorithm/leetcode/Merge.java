package com.nanophase.algorithm.leetcode;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @date 2021-04-13
 * @apiNote 合并两个有序数组
 * <p>
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 示例 2：
 * <p>
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Merge {
    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {1};
        int m = 0;
        int n = 1;
        merge(nums1, nums2, m, n);
        SortUtil.print(nums1);
    }

    /**
     * 一位用户的写法
     *
     * @param nums1
     * @param nums2
     * @param m
     * @param n
     */
    private static void merge(int[] nums1, int[] nums2, int m, int n) {
        int tail = nums1.length - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        while (p2 >= 0) {
            if (p1 < 0 || nums1[p1] <= nums2[p2]) {
                nums1[tail--] = nums2[p2--];
            } else {
                nums1[tail--] = nums1[p1--];
            }
        }
    }

    /**
     * 合并两个有序数组 合并完成后仍然保持有序
     * 我的思路：有可能数组会非常大，如果从数组1中插入一个值，该值后面的所有值都会往后挪一位，消耗资源,不可取;
     * 假设将nums2的值先合并到nums1的空缺位置，然后再进行一次排序，可以减少很多元素的交换次数
     *
     * @param nums1 有序数组1
     * @param nums2 有序数组2
     * @param m     有序数组1的有序长度
     * @param n     有序数组2的有序长度
     */
    private static void merge1(int[] nums1, int[] nums2, int m, int n) {
        if (m > nums1.length || n > nums2.length) {
            return;
        }
        if (nums2.length < 1 || n < 1) {
            return;
        }
        // 将nums2的元素合并到nums1的尾部空缺位置
        int i = m;
        int j = 0;
        // 这里假设了nums1的剩余空间足够保存nums2的元素
        while (i < nums1.length && j < n) {
            nums1[i++] = nums2[j++];
        }
        // 未排序之前，如果nums2的第一个值大于nums1的最后一个值，无需排序，是有序的
//        if (nums1.length > 1 && nums1[m] > nums1[m - 1]) {
//            return;
//        }
        // 进行排序 直接使用插入排序 这样有序数组就没有意义了
        for (i = 0, j = i + 1; j < nums1.length; i++, j++) {
            while (nums1[j] < nums1[i]) {
                int temp = nums1[i];
                nums1[i] = nums1[j];
                nums1[j] = temp;
                if (i != 0) {
                    i--;
                }
                if (j > 1) {
                    j--;
                }
            }
        }
    }
}
