package com.nanophase.algorithm.leetcode;

/**
 * @author zhj
 * @date 2021-04-08
 * @apiNote 整数反转 来源-leetcode
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围[−231,231− 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Reverse {
    public static void main(String[] args) {
        int num = -2147;
        System.out.println(num);
        System.out.println(reverse(num));
    }

    private static int reverse(int num) {
        if (0 <= num && 10 > num) {
            return num;
        }
        String str = Integer.valueOf(num).toString();
        if (num < 0){
            str = str.substring(1);
        }
        StringBuilder builder = new StringBuilder();
        if (num < 0) {
            builder.append("-");
        }
        char[] chars = str.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            builder.append(chars[i]);
        }
        long aLong = Long.parseLong(builder.toString());
        if (aLong > Integer.MAX_VALUE || aLong < Integer.MIN_VALUE){
            return 0;
        }
        return Integer.parseInt(Long.toString(aLong));
    }
}
