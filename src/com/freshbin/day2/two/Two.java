package com.freshbin.day2.two;

import java.util.LinkedList;
import java.util.List;

/**
 *  2.使用 wait notify 实现一个队列，队列有2个方法，add 和 get 。
 *  add方法往队列中添加元素，get方法往队列中获得元素。队列必须是线程安全的。
 *  如果get执行时，队列为空，线程必须阻塞等待，直到有队列有数据。
 *  如果add时，队列已经满，则add线程要等待，直到队列有空闲空间。
 *  实现这么一个队列，并写一个测试代码，使他工作在多线程的环境下，证明，它的工作是正确的。
 * 
 * @author freshbin
 * @date 2019年2月26日 下午3:05:41
 */
public class Two {
	public static void main(String[] args) throws InterruptedException {
		List<Integer> myList = new LinkedList<>();
		for(int i = 0; i < 5; i++) {
			myList.add(i);
		}
		
		Thread producer = new Thread(new Producer(myList, 5));
		producer.start();
		
		Thread consumer = new Thread(new Consumer(myList));
		consumer.start();
	}
}
