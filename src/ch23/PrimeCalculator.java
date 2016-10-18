package ch23;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class PrimeCalculator extends SwingWorker<Integer,Integer>{

	private static final SecureRandom generator = new SecureRandom();
	private final JTextArea textArea;
	private final JButton cancelBtn;
	private final JButton getPrimesBtn;
	private final JLabel statusLbl;
	private final boolean[] primes;
	
	public PrimeCalculator(int max, JTextArea textArea, JButton cancelBtn,
			JButton getPrimesBtn, JLabel statusLbl){
		this.textArea = textArea;
		this.cancelBtn = cancelBtn;
		this.getPrimesBtn = getPrimesBtn;
		this.statusLbl = statusLbl;
		primes = new boolean[max];
		Arrays.fill(primes, true);
	}
	
	@Override
	protected Integer doInBackground() throws Exception {
		int count = 0;
		
		for(int i = 2; i < primes.length; i++){
			if(isCancelled())
				return count;
			else{
				setProgress(100 *(i+1)/primes.length);
				try{
					Thread.sleep(generator.nextInt(5));
				}catch(InterruptedException e){
					statusLbl.setText("Worker thread interrupted");
					return count;
				}
				
				if(primes[i]){
					publish(i);
					++count;
					for(int j = i + i; j < primes.length; j += i)
						primes[j] = false;
						
				}//end of if(prime[])
					
			}//end of else
		}//end of outer for
		return count;
	}//end of doInBackground
	
	protected void process(List<Integer> publishedVals){
		for(int i = 0; i < publishedVals.size(); i++)
			textArea.append(publishedVals.get(i) + "\n");
	}

	protected void done(){
		getPrimesBtn.setEnabled(true);
		cancelBtn.setEnabled(false);
		try {
			statusLbl.setText("Found "+ get()+ " primes.");
		} catch (InterruptedException | ExecutionException | CancellationException e) {
			// TODO Auto-generated catch block
			statusLbl.setText(e.getMessage());
		}//end of try-catch
	}//end of done
}
