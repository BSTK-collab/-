package com.nanophase.algorithm.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列 延时执行
 * 延时主要使用的锁的能力，比如需要延时5s执行。那么会sleep5s，被唤醒时，如果能获取到资源，则执行
 * 类注释信息：
 * 1，队列中的元素在过期时被执行，越靠近队头，越早执行
 * 2，元素在未过期时，不允许take
 * 3，不允许空元素
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        // 泛型必须继承Delayed，并重写compareTo,这会用来对过期元素进行排序
        // take时如果元素没有过期则会无限阻塞，一直到有元素过期
        // 如果不想无限阻塞，可以使用poll,设置过期时间，时间内，如果队头元素没有过期的话，会返回null
        DelayQueue<demo> delayQueue = new DelayQueue<>();
    }

    class demo implements Delayed {

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
