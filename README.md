# coreJava17-interview-prepartion

	####//short keys:- idea ide java
				//press double/two times shift key to open search
		//control + D . at end of a line press control + d to make duplicate new line 
    //ctrl + alt to correct identation
    //alt + enter to create/show suggestion to create local variable# coreJava17-learning-program




Sure! In Java, multithreading is a core feature that allows concurrent execution of two or more threads. Understanding the different thread methods—sleep(), yield(), wait(), notify(), and join()—is crucial for effective thread management. Here's a brief description of each, along with real-time examples.

1. sleep(long millis)
   Description: Pauses the execution of the current thread for a specified duration (in milliseconds). It doesn't release any locks.
   Example: Simulating a download process.
   java
   Copy code
   class DownloadTask extends Thread {
   public void run() {
   System.out.println("Download started.");
   try {
   // Simulate download time
   Thread.sleep(5000);
   } catch (InterruptedException e) {
   e.printStackTrace();
   }
   System.out.println("Download completed.");
   }
   }

public class SleepExample {
public static void main(String[] args) {
DownloadTask task = new DownloadTask();
task.start();
}
}
2. yield()
   Description: Suggests to the thread scheduler that the current thread is willing to yield its current use of the CPU. This method does not guarantee that another thread will start running; it's just a hint.
   Example: Threads attempting to process tasks in a round-robin fashion.
   java
   Copy code
   class Task extends Thread {
   public void run() {
   for (int i = 0; i < 5; i++) {
   System.out.println(Thread.currentThread().getName() + " is executing.");
   Thread.yield(); // Suggest to yield the CPU
   }
   }
   }

public class YieldExample {
public static void main(String[] args) {
Task t1 = new Task();
Task t2 = new Task();
t1.start();
t2.start();
}
}
3. wait()
   Description: Causes the current thread to wait until another thread invokes notify() or notifyAll() on the same object. It releases the lock on the object.
   Example: A producer-consumer scenario.
   java
   Copy code
   class SharedResource {
   private int data;
   private boolean available = false;

   public synchronized int consume() throws InterruptedException {
   while (!available) {
   wait(); // Wait until data is available
   }
   available = false;
   notify(); // Notify producer
   return data;
   }

   public synchronized void produce(int value) throws InterruptedException {
   while (available) {
   wait(); // Wait until data is consumed
   }
   data = value;
   available = true;
   notify(); // Notify consumer
   }
   }

class Producer extends Thread {
SharedResource resource;

    Producer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                resource.produce(i);
                System.out.println("Produced: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
SharedResource resource;

    Consumer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                int value = resource.consume();
                System.out.println("Consumed: " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class WaitNotifyExample {
public static void main(String[] args) {
SharedResource resource = new SharedResource();
new Producer(resource).start();
new Consumer(resource).start();
}
}
4. notify() and notifyAll()
   Description: Wakes up a single thread (or all waiting threads, if notifyAll() is called) that is waiting on the object's monitor.
   Example: This is already demonstrated in the producer-consumer scenario above.
5. join()
   Description: Waits for a thread to die. When one thread calls join() on another, it blocks until that thread terminates.
   Example: Ensuring one thread completes before proceeding in another thread.
   java
   Copy code
   class TaskWithJoin extends Thread {
   public void run() {
   for (int i = 1; i <= 3; i++) {
   System.out.println(Thread.currentThread().getName() + " executing task " + i);
   try {
   Thread.sleep(1000);
   } catch (InterruptedException e) {
   e.printStackTrace();
   }
   }
   }
   }

public class JoinExample {
public static void main(String[] args) {
TaskWithJoin task1 = new TaskWithJoin();
TaskWithJoin task2 = new TaskWithJoin();
task1.start();
try {
task1.join(); // Main thread waits for task1 to complete
} catch (InterruptedException e) {
e.printStackTrace();
}
task2.start(); // Starts task2 after task1 finishes
}
}
Summary
sleep(): Temporarily halts the thread.
yield(): Suggests the thread scheduler to give up the current CPU time slice.
wait()/notify(): Used for inter-thread communication, particularly in producer-consumer scenarios.
join(): Ensures one thread completes before another continues.
These methods help manage thread execution effectively in Java applications.