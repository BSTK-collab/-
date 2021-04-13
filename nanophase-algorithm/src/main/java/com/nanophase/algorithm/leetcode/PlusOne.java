package com.nanophase.algorithm.leetcode;

import com.nanophase.algorithm.common.SortUtil;

/**
 * @author zhj
 * @date 2021-03-09
 * @apiNote 加一
 * <p>
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例1：
 * <p>
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例2：
 * <p>
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 * <p>
 * 输入：digits = [0]
 * 输出：[1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] arr = {3, 4, 9, 5};
        int[] result = plusOne(arr);
        SortUtil.print(result);
    }

    /**
     * 我的思路，首先不适合转成int或者long，因为数组的长度充满不确定性
     * 如果尾数 = 9，arr[i] = 0; arr[i - 1] + 1; if(i == 0 && arr[i] == 9) 数组扩容
     *
     * @param arr
     * @return
     */
    private static int[] plusOne(int[] arr) {
        int length = arr.length;
        int[] result = new int[length];
        // 拷贝数组
        System.arraycopy(arr, 0, result, 0, length);
        // 尾数小于9的情况
        if (result[length - 1] < 9) {
            result[length - 1] += 1;
            return result;
        }

        // 只有一个9的数组
        if (length == 1 && arr[0] == 9) {
            return new int[]{1, 0};
        }

        // 考虑尾数为9的情况 本位置的值 = 0；i - 1的位置 = 0 || +1；
        int i = length - 1;
        if (arr[i] + 1 > 9) {
            result[i] = 0;
            while (i > 0 && arr[i] + 1 > 9) {
                result[i - 1] = result[i - 1] + 1 > 9 ? 0 : result[i - 1] + 1;
                i--;
            }

            if (i == 0 && arr[0] == 9 && arr[1] == 9) {
                // 数组需要扩容
                int[] ints = new int[length + 1];
                ints[0] = 1;
                return ints;
            }
        }
        return result;
    }
}
