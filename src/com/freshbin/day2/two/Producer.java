package com.freshbin.day2.two;

import java.util.LinkedList;
import java.util.List;

/**
 * 生产者类
 * 
 * @author freshbin
 * @date 2019年2月26日 下午3:31:57
 */
public class Producer implements Runnable {
	private List<Integer> myList;
	private Integer size;
	public static int count = 50;
	public static boolean isBreak = false;

	public Producer(List<Integer> myList, Integer size) {
		this.myList = myList;
		this.size = size;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				if(Consumer.isBreak) {
					System.out.println("消费者线程已中断!生产者也自动中断!");
					break;
				}
				
				if(Thread.currentThread().isInterrupted()) {
					System.out.println("生产者线程中断!");
					isBreak = true;
					break;
				}
				
				synchronized (myList) {
					if(myList.size() >= size) {
						System.out.println("已经满了!");
						myList.notifyAll();
						myList.wait();
					} else {
						count--;
						int number = (int)(Math.random()*10);
						myList.add(number);
						System.out.println("生产了一个:" + number);
						if(count <= 0) {
							System.out.println("生产者达到最大次数!");
							myList.notifyAll();
							Thread.currentThread().interrupt();
						}
						myList.notifyAll();
					}
				}
				
				if(!Thread.currentThread().isInterrupted()) {
					Thread.sleep(100);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
