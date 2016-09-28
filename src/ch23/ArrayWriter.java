package ch23;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArrayWriter implements Runnable {
	
	public static void main(String [] args){
		
		SimpleArray array = new SimpleArray(6);
		ArrayWriter aw1 = new ArrayWriter(1,array);
		ArrayWriter aw2 = new ArrayWriter(6,array);
		
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(aw1);
		es.execute(aw2);
		es.shutdown();
		
		try{
			boolean taskEnded = es.awaitTermination(1, TimeUnit.MINUTES);
			if(taskEnded){
				System.out.printf("%nContents of Array%n");
				System.out.println(array);
			}else
				System.out.println("Timed out while waiting for thread to finish");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
	private final SimpleArray simpleSharedArray;
	private final int startValue;

	public ArrayWriter(int value, SimpleArray array){
		startValue = value;
		simpleSharedArray = array;
	}
	@Override
	public void run() {
		for(int i = startValue; i < startValue+3; i++){
			simpleSharedArray.add(i);
		}
		
	}

}
