package com.nanophase.algorithm.thread;

/**
 * @author zhj
 * @date 2021-04-27
 * @apiNote 测试join方法
 */
public class TestJoin2 {
    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                System.out.println("A" + i);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 40; i++) {
                System.out.println("B" + i);

                try{
                    Thread.sleep(500);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // 虽然线程2先执行，但是第一行代码调用了Thread1的join，所以会先执行Thread1
        thread2.start();
        Thread.sleep(500);
        thread1.start();
    }
}
