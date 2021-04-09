package com.nanophase.algorithm.leetcode;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @date 2021-04-09
 * @apiNote 在O(1)的空间条件下删除数组中的重复项
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 0, 0, 0, 1};
        int size = removeDuplicates(arr);
        System.out.println("数组：");
        SortUtil.print(arr);
        System.out.println();
        System.out.println(size);
    }

    /**
     * leetcode官方思路：慢指针i和快指针j，只要arr[i] == arr[j]，就增加j的跳过重复项
     * 将arr[j]的值放到arr[i + 1]的位置上，然后递增i，重复操作,一直到j遍历到数组的尾部
     *
     * @param arr
     * @return
     */
    private static int removeDuplicates(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        // 这个判断是我加的
        if (arr[0] == arr[arr.length - 1]) {
            return 1;
        }
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[i] != arr[j]) {
                arr[++i] = arr[j];
            }
        }
        return i + 1;
    }

    /**
     * 我的思路: 由于是排好序的数组，直接找大的值，找到后放到 i + 1的位置，把重复的值覆盖掉
     * 总感觉哪里可以优化
     *
     * @param arr
     * @return
     */
    private static int removeDuplicates1(int[] arr) {
        // 数组很小或首尾相等
        if (arr.length < 2 || arr[0] == arr[arr.length - 1]) {
            return 1;
        }
        /*
         * 由于数组为有序数组，校验出数组的有序方向。假设数组的有序方向为从小到大，剔除重复元素的步骤为:
         * i = 第一个元素；j = 第二个元素
         * 数组边界内死循环，直到找到大的数交换过来
         * */
        for (int i = 0, j = 1; j < arr.length; i++, j++) {
            while (j < arr.length) {
                if (arr[j] > arr[i]) {
                    // 找到了大的数 拿过来
                    arr[i + 1] = arr[j];
                    break;
                }
                j++;
            }
        }
        int k = 1;
        for (int i = 0, j = 1; j < arr.length && arr[j] > arr[i]; i++, j++) {
            k++;
        }
        return k;
    }
}
