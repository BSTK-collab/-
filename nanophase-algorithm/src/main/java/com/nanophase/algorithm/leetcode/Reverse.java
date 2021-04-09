package com.nanophase.algorithm.leetcode;

/**
 * @author zhj
 * @date 2021-04-08
 * @apiNote 整数反转 来源-leetcode
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围[−231,231− 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 * <p>
 * 输入：x = -123
 * 输出：-321
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Reverse {
    public static void main(String[] args) {
        // Integer.MAX_VALUE = 2147483647
        // Integer.MIN_VALUE = -2147483648
        int num = -123;
        System.out.println(reverse(num));
    }

    /**
     * 参考leetcode官方，借用数学方法将最后一位数字推送到rev的前面
     * 但是需要提前判断是否会整数一处
     *
     * @param num
     * @return
     */
    private static int reverse(int num) {
        if (0 <= num && 10 > num) {
            return num;
        }
        int result = 0;
        while (num != 0) {
            // 通过取余 获取最后一位数字
            int pre = num % 10;
            // 每次减一位，获取循环次数; 1 ~ 9 / 10 = 0
            num /= 10;

            // 为了避免倒序后可能发生的整数越界，需要每次对result进行计算时加以判断
            // -2147483648 / 10 = -214748364 如果result为负数，乘法计算后会变得更小,可能越界
            // 在leetcode官方的解析中判断条件为：
            // if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0
            // if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (Integer.MIN_VALUE / 10 - pre > result) {
                return 0;
            }
            if (Integer.MAX_VALUE / 10 - pre < result) {
                return 0;
            }
            /*
             * result * 10是进位操作，比如数字123进行倒序
             * 通过取余得到3后，num /= 10 = 12;
             * 再次取余得到2，合并到result的公式为 result = 3 * 10 + 2 = 32
             * 123的最终倒序 = 32 * 10 + 1 = 321
             * */
            result = result * 10 + pre;
        }
        return result;
    }

    /**
     * 直接转换成字符数组进行拼接
     *
     * @param num
     * @return
     */
    private static int reverse1(int num) {
        if (0 <= num && 10 > num) {
            return num;
        }
        String str = Integer.valueOf(num).toString();
        if (num < 0) {
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
        if (aLong > Integer.MAX_VALUE || aLong < Integer.MIN_VALUE) {
            return 0;
        }
        return Integer.parseInt(Long.toString(aLong));
    }
}
