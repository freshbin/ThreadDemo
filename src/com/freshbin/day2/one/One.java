package com.freshbin.day2.one;

/**
 *  1.现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行
 * 
 * @author freshbin
 * @date 2019年2月26日 下午3:05:41
 */
public class One implements Runnable {

	@Override
	public void run() {
		System.out.println("当前线程名称：" + Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread one = new Thread(new One());
		one.setName("线程one");
		one.start();
		one.join();
		
		Thread two = new Thread(new One());
		two.setName("线程two");
		two.start();
		two.join();
		
		Thread three = new Thread(new One());
		three.setName("线程three");
		three.start();
		three.join();
	}
}
