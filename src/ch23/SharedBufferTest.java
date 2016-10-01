package ch23;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedBufferTest {

	public static void main(String [] args) throws InterruptedException{
		ExecutorService es = Executors.newCachedThreadPool();
		
		Buffer sharedLocation = new BlockingBuffer();
		
		System.out.printf("Action\t\tValue\tSum of Produced\tSum of Consumed%n");
		System.out.printf("------\t\t-----\t---------------\t---------------%n%n");
		
		es.execute(new Producer(sharedLocation));
		es.execute(new Consumer(sharedLocation));
		es.shutdown();
		es.awaitTermination(1, TimeUnit.MINUTES);
	}
}
