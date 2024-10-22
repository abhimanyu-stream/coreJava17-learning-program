package com.java17.interview.prepartion;

public class ImplementsRunnableInterface {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Main Thread Start "+ Thread.currentThread().getName());

		Runnable r = ()->{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			for(int i = 0; i < 5; i++){

				System.out.println("Child Thread Implementation :" + Thread.currentThread().getName()+ "  " + i);
			}
			System.out.println("Child Thread End "+ Thread.currentThread().getName());
		};
		Thread t = new Thread(r);
		t.setName("worker-1");
		t.start();
		Thread t2 = new Thread(r);
		t2.setName("worker-2");
		t2.start();
		//We can prevent(stop) a Thread execution by using the following methods.
		//1) yield();
		// yield() method causes “to pause current executing Thread for giving the chance of remaining waiting Threads of same priority”.
 		//If all waiting Threads have the low priority or if there is no waiting Threads then the same Thread will be continued its execution.
 		//If several waiting Threads with same priority available then we can’t expect exact which Thread will get chance for execution.
		//The Thread which is yielded when it get chance once again for execution is depends on mercy of the Thread scheduler.
 		//public static native void yield();
        //2) join();
        //If a Thread wants to wait until completing some other Thread then we should go for join() method.
        //1) public final void join()throws InterruptedException
        //2) public final void join(long ms) throws InterruptedException
        //3) public final void join(long ms,int ns) throws InterruptedException
        //3) sleep();
        //If a Thread don’t want to perform any operation for a particular amount of time then we should go for sleep() method.
        //1) public static native void sleep(long ms) throws InterruptedException
        //2) public static void sleep(long ms,int ns)throws InterruptedException
        //Thread.yield();// yield is a static method
        //Thread.sleep();// sleep is a static method
        //t.join();// join is instance method
        System.out.println("Main Thread End");
		Object o = new Object();
		//Method	Description
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



	}

}
