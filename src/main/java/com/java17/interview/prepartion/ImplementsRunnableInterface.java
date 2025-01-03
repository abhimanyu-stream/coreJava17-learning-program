package com.java17.interview.prepartion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImplementsRunnableInterface {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//System.out.println("Main Thread Start "+ Thread.currentThread().getName());


		log.info("Main Thread Start "+ Thread.currentThread().getName());
		Runnable r = ()->{
			//child Thread area starts
			try {
				Thread.sleep(1000);//It's for child Thead[ any one who will come here("worker-1","worker-2")}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			for(int i = 0; i < 5; i++){

				System.out.println("Child Thread Implementation :" + Thread.currentThread().getName()+ "  " + i);
				Thread.yield();//It's for child Thead[ any one who will come here("worker-1","worker-2")}
			}
			try {
				Thread.sleep(3);//It's for child Thead[ any one who will come here("worker-1","worker-2")}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			System.out.println("Child Thread End "+ Thread.currentThread().getName());
			//child Thread area ends
		};
		Thread.sleep(4);//It's for Main Thead
		Thread t = new Thread(r);//Main Thead is doing this
		t.setName("worker-1");//Main Thead is doing this
		t.start();//Main Thead is doing this
		Thread t2 = new Thread(r);//Main Thead is doing this
		t.join();// Main thread waits for task t to complete[//Main Thead is doing this]
		System.out.println("*********task has been completed by thread t********"+ t.getName());

		t2.setName("worker-2");//Main Thead is doing this
		//t.notifyAll();//Exception in thread "main" java.lang.IllegalMonitorStateException: current thread is not owner
		//Main Thead is doing this
		t2.start(); // Starts task t2 after task t finishes[//Main Thead is doing this]
		t2.join();// Main thread waits for task t2 to complete[//Main Thead is doing this]

		System.out.println("*********task has been completed by thread t2********"+ t2.getName());
		log.info("some");//Main Thead is doing this
		//t2.notifyAll();//Exception in thread "main" java.lang.IllegalMonitorStateException: current thread is not owner
		//Main Thead is doing this



		//We can prevent(stop) a Thread execution by using the following methods.

		//1) yield();

		// yield() method causes “to pause current executing Thread for giving the chance of remaining waiting Threads of same priority”.
 		//If all waiting Threads have the low priority or if there is no waiting Threads then the same Thread will be continued its execution.
 		//If several waiting Threads with same priority available then we can’t expect exact which Thread will get chance for execution.
		//The Thread which is yielded when it get chance once again for execution is depends on mercy of the Thread scheduler.
 		//public static native void yield();
		//Thread.yield();// yield is a static method

		//2) join();

        //If a Thread wants to wait until completing some other Thread, then we should go for join() method.
        //1) public final void join()throws InterruptedException
        //2) public final void join(long ms) throws InterruptedException
        //3) public final void join(long ms,int ns) throws InterruptedException
		//t.join();// join is instance method

		//3) sleep();

        //If a Thread don’t want to perform any operation for a particular amount of time then we should go for sleep() method.
        //1) public static native void sleep(long ms) throws InterruptedException
        //2) public static void sleep(long ms,int ns)throws InterruptedException
        //Thread.sleep();// sleep is a static method


		//wait()/notify(): Used for inter-thread communication, particularly in producer-consumer scenarios.


		Object o = new Object();
		// 11 Methods	Description
		//public final Class getClass()	Retrieves the Class object associated with the instance.
		//public int hashCode()	Computes and returns a unique hash code for this object.
		//public boolean equals(Object obj)	Determines whether this object is equal to another object.
		//protected Object clone()	Generates and provides a shallow copy of this object.
		//public String toString()	Offers a string representation of this object.
		//public final void notify()	Awakens a single thread waiting on this object.
		//public final void notifyAll()	Awakens all threads waiting on this object.
		//public final void wait(long timeout) throws InterruptedException	Directs the current thread to wait until another thread triggers the notify() or notifyAll() method for this object.
		//public final void wait(long timeout, int nanos) throws InterruptedException	Instructs the current thread to wait until another thread invokes the notify() or notifyAll() method for this object.
		//public final void wait() throws InterruptedException	Prompts the current thread to wait until another thread invokes the notify() or notifyAll() method for this object.
		//protected void finalize() throws Throwable	Executes when the garbage collector identifies no more references to the object, signaling its readiness for collection.



		System.out.println("Main Thread End");
	}

}

/**
 * 3. wait()
 * Description: Causes the current thread to wait until another thread invokes notify() or notifyAll() on the same object. It releases the lock on the object immediately.
 *
 * **/

// https://www.youtube.com/watch?v=4aYvLz4E1Ts&t=4s
//🟢 Description:
		//Unlock the power of Java multithreading with our comprehensive guide! In this video, we cover key concepts including synchronization, reentrant locks, read-write locks, deadlock prevention, CountdownLatch, CyclicBarrier, Runnable, Callable, ExecutorService, and CompletableFuture. Whether you're a beginner or an advanced developer, this tutorial will help you understand and implement efficient multithreading in your Java applications. Watch now to enhance your Java skills and boost your coding efficiency!
