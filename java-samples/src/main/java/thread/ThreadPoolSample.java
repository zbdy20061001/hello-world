package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//https://www.yiibai.com/java_multithreading/java-thread-pool.html
//https://www.liaoxuefeng.com/wiki/1252599548343744/1306581130018849
public class ThreadPoolSample implements Runnable {
    private String message;

    public ThreadPoolSample(String s) {
        this.message = s;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " (Start) message = " + message);
        processmessage();// call processmessage method that sleeps the thread for 2 seconds
        System.out.println(Thread.currentThread().getName() + " (End)");// prints thread name
    }

    private void processmessage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads  
	       for (int i = 0; i < 100; i++) {  
	           Runnable worker = new ThreadPoolSample("" + i);  
	           executor.execute(worker);//calling execute method of ExecutorService  
	         }  
	       executor.shutdown();  
	       while (!executor.isTerminated()) {   }  

	       System.out.println("Finished all threads");  
	}
}
