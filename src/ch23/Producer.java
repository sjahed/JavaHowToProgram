package ch23;

import java.security.SecureRandom;

public class Producer implements Runnable{
	
	public static final SecureRandom generator = new SecureRandom();
	public final Buffer sharedLocation;
	
	public Producer(Buffer sharedLocation){
		this.sharedLocation = sharedLocation;
	}

	@Override
	public void run() {
		
		int sum = 0;
		for(int count = 0; count <= 10; count++){
			try{
				Thread.sleep(generator.nextInt(5000));
				sharedLocation.blockingPut(count);
				sum += count;
				//System.out.printf("\t%2d%n", sum);
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
		}
		System.out.printf("Producer done producing%nTerminating Producer%n");
	}

}
