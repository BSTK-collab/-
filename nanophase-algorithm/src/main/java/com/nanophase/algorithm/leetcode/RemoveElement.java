package com.nanophase.algorithm.leetcode;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @date 2021-04-09
 * @apiNote 在O(1)的空间条件下移除元素 来源-leetcode
 * <p>
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] arr = {0};
        int val = 0;
        int size = removeElement(arr, val);
        SortUtil.print(arr);
        System.out.println();
        System.out.println(size);
    }

    /**
     * 官方解题思路(恐怖)：双指针策略 i = 0,j = arr.length
     * 如果arr[i] == val,将数组的最后一个值放到i的位置，j--，如果这个值还是等于val,那么下次循环还会和j交换
     * @param arr
     * @param val
     * @return
     */
    private static int removeElement(int[] arr, int val) {
        int i = 0;
        int j = arr.length;
        while (i < j) {
            if (arr[i] == val) {
                arr[i] = arr[j - 1];
                j--;
            }else {
                i++;
            }
        }
        return j;
    }


    /**
     * 我的思路：
     * 声明两个指针，i指针指向开始位置，j指针指向数组结束位置；
     * 如果arr[i] != val则一直循环，i一直自增，遍历结束后如果还没有则直接返回；如果找到了与val相同的值，
     * 遍历j指针，找到可以交换的值，与i进行交换
     * <p>
     *
     * @param arr
     * @param val
     * @return
     */
    private static int removeElement1(int[] arr, int val) {
        if (arr.length == 0) {
            return 0;
        }
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            // 如果没有找到val值，那就一直自增下去，直到结束
            if (arr[i] != val) {
                i++;
                continue;
            }
            // 从数组中找到了val值，获取可以交换的位置
            while (j > i && arr[j] == val) {
                // 找到可以交换的位置
                j--;
            }
            if (i < j && arr[j] != val) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            if (j == i) {
                // 所有的值都是一样的
                return i;
            }
        }
        return i;
    }
}
