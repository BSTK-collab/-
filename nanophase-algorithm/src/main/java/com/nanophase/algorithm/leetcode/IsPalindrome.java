package com.nanophase.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhj
 * @date 2021-04-08
 * @apiNote 判断回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsPalindrome {
    public static void main(String[] args) {
        int num = 121;
        System.out.println(isPalindrome(num));
    }

    /**
     * 参考了leetcode
     * 思想为：如果该数字不是回文数，那么反转时可能有int溢出的风险，leetcode的核心思想是反转一半 ，后半部分数字
     * 如何反转后半部分数字？
     * 假设以1221为例，我们可以通过%方式获取最后一位数字 = 1，可以通过 /10 % 10的方式获取倒数第二位数字 = 2
     * 反转后半部分的数字 = 1 * 10 + 2 = 12；就得到了需要反转的数字
     * 原始数字随着计算会不断变小，需要反转的数字会不断增大，如果，原始数字小于或等于反转数字，说明已经到一半了
     *
     * @param num
     * @return
     */
    private static boolean isPalindrome(int num) {
        if (num < 0 || (0 == num % 10 && 0 != num)) {
            return false;
        }
        if (num < 10) {
            return true;
        }
        int rev = 0;
        while (num > rev) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }
        return num == rev || num == rev / 10;
    }

    /**
     * 规则限制，负数不是回文数
     * leetcode击败了百分之五的用户
     *
     * @param num
     * @return
     */
    private static boolean isPalindrome1(int num) {
        if (num < 0 || (0 == num % 10 && 0 != num)) {
            // 负数不是回文数 以0结尾的整数也不是
            return false;
        }
        if (num < 10) {
            // 只有一位数字 是回文数
            return true;
        }
        List<Integer> list = new ArrayList<>();
        for (long j = 1; num / j > 0; j *= 10) {
            long l = num / j % 10;
            list.add(Integer.valueOf(Long.toString(l)));
        }
        for (int i1 = 0, k = list.size() - 1; i1 < k; i1++, k--) {
            if (!list.get(i1).equals(list.get(k))) {
                return false;
            }
        }
        return true;
    }
}
