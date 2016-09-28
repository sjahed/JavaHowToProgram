package ch23;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintTask implements Runnable {

	public static void main(String [] args){
		PrintTask t1 = new PrintTask("Task One");
		PrintTask t2 = new PrintTask("Task Two");
		PrintTask t3 = new PrintTask("Task Three");
		
		System.out.println("Starting Executor");
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(t1);
		es.execute(t2);
		es.execute(t3);
		
		es.shutdown();
		System.out.println("Tasks started. Main ended.");
	}
	
	private static final SecureRandom generator = new SecureRandom();
	private final int sleepTime;
	private final String taskName;
	
	public PrintTask(String taskName){
		this.taskName = taskName;
		sleepTime = generator.nextInt(5000);
	}
	@Override
	public void run() {
		
		try{
			System.out.printf("%s going to sleep for %d millseconds.%n",taskName,sleepTime);
			Thread.sleep(sleepTime);
		}catch(InterruptedException exception){
			exception.printStackTrace();
			Thread.currentThread().interrupt();
		}
		
		System.out.printf("%s done sleeping.%n", taskName);
	}

}
