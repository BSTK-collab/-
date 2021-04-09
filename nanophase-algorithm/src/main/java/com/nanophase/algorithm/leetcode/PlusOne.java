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
        int[] arr = {1, 2, 9};
        int[] result = plusOne(arr);
        SortUtil.print(result);
    }

    /**
     * 我的思路，首先不适合转成int或者long，因为数组的长度充满不确定性
     *
     * @param arr
     * @return
     */
    private static int[] plusOne(int[] arr) {
        int length = arr.length;
        if (arr[length - 1] + 1 < 10) {
            arr[length - 1] += 1;
            return arr;
        }
        // 有进位的情况 需要建立新数组
        int[] result = new int[length];
        if (arr[0] == 9) {
            result = new int[length + 1];
        }
        // 进位后末尾必然是0
        result[result.length - 1] = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] < 9 && arr[i + 1] == 9) {
                result[i] = arr[i] + 1;
            }else {
                result[i] = arr[i];
            }
        }
        return result;
    }
}
