package com.freshbin.day2.two;

import java.util.LinkedList;
import java.util.List;

/**
 * 消费者类
 * 
 * @author freshbin
 * @date 2019年2月26日 下午3:31:57
 */
public class Consumer implements Runnable {
	private List<Integer> myList;
	public static int count = 50;
	public static boolean isBreak = false;

	public Consumer(List<Integer> myList) {
		this.myList = myList;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				if(Producer.isBreak) {
					System.out.println("生产者线程已中断!消费者也自动中断!");
					break;
				}
				
				if(Thread.currentThread().isInterrupted()) {
					System.out.println("消费者线程中断!");
					isBreak = true;
					break;
				}
				
				synchronized (myList) {
					if(myList.size() > 0) {
						count--;
						int number = myList.get(myList.size() - 1);
						myList.remove(myList.size()-1);
						System.out.println("消费了一个:" + number);
						if(count <= 0) {
							System.out.println("消费者次数达到最大!");
							myList.notifyAll();
							Thread.currentThread().interrupt();
						}
						myList.notifyAll();
					} else {
						System.out.println("没有数量了!");
						myList.notifyAll();
						myList.wait();
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
