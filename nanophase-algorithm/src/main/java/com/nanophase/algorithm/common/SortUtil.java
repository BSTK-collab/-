package com.nanophase.algorithm.common;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhj
 * @apiNote 排序算法的一些辅助工具类
 * @since 2021-03-24
 */
public class SortUtil {

    /**
     * 循环校验次数
     */
    public static final int verifyCount = 3000;

    /**
     * 打印数据
     *
     * @param arr
     */
    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * 对数器 校验排序算法结果与系统排序算法的结果是否一致 // TODO: 2021/3/24 无法通过反射获取返回值
     *
     * @param arr     未排序的数据
     * @param sortArr 自定义排序算法排序后的数组
     * @return
     */
    public static boolean verifyArray(int[] arr, int[] sortArr) {
        boolean flag = true;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != sortArr[i]) {
                System.out.println("正确值：" + arr[i]);
                System.out.println("错误值：" + sortArr[i]);
                flag = false;
            }
        }
        if (!flag) {
            System.out.println("错误的排序：");
            print(sortArr);
            System.out.println();
            System.out.println("=====================================");
            System.out.println("正确的排序：");
            print(arr);
        }
        return flag;
//        try {
//            Method sort = clazz.getMethod("sort", int[].class);
//            int[] arr = {1, 2, 3};
//            Object invoke = sort.invoke(clazz.getDeclaredConstructor().newInstance(), arr);
//            sort.setAccessible(true);
//            Type genericReturnType = sort.getGenericReturnType();
//
//            System.out.println("获取到的返回值" + genericReturnType.getTypeName());
//
//        } catch (NoSuchMethodException | InstantiationException e) {
//            System.out.println("获取方法实例失败");
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            System.out.println("获取sort方法失败...");
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            System.out.println("sort方法 invoke失败...");
//            e.printStackTrace();
//        }
//        return false;
    }

    /**
     * 获取随机数组
     *
     * @param length 可指定长度
     * @return
     */
    public static int[] getRandomArray(int length) {
        Random random = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }

    /**
     * 交换位置
     *
     * @param arr 数组
     * @param i   位置
     * @param j   位置
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 返回数组的最大值和最小值
     *
     * @param arr   取值的数组
     * @param left  取值范围-左指针
     * @param right 取值范围-右指针
     * @return 返回数组结构，0为最大值索引位置；1为最小值索引位置
     */
    public static int[] findMaxAndMinValue(int[] arr, int left, int right) {
        if (arr.length < 2 || right - left < 2) {
            return arr;
        }
        int minVal = arr[left];
        int maxVal = arr[left];
        for (int k = left + 1; k < right; k++) {
            if (arr[k] < minVal) {
                minVal = arr[k];
            }
            if (arr[k] > maxVal) {
                maxVal = arr[k];
            }
        }
        return new int[]{maxVal, minVal};
    }
}