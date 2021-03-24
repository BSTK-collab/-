package com.nanophase.algorithm.common;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhj
 * @apiNote 排序算法的一些辅助工具类
 * @since 2021-03-24
 */
public class sortUtil {

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
            System.out.println(i + " ");
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
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != sortArr[i]) {
                return false;
            }
        }
        return true;
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
            arr[i] = random.nextInt();
        }
        return arr;
    }

    /**
     * 交换位置
     *
     * @param arr 数组
     * @param i 位置
     * @param j 位置
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}