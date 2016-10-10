package ch23;

import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class BackgroundCalculator extends SwingWorker<Long, Object> {

	private final int n;
	private final JLabel resultJLabel;
	
	public BackgroundCalculator(int n, JLabel resultJLabel){
		this.n = n;
		this.resultJLabel = resultJLabel;
	}
	
	@Override
	protected Long doInBackground() {
		return fibonacci(n);
	}

	protected void done(){
		try{
			resultJLabel.setText(get().toString());
		}catch(InterruptedException es){
			resultJLabel.setText("Interrupted while calculating");
		}catch(ExecutionException es){
			resultJLabel.setText("Error encountered while trying to caluclate");
		}
	}
	private long fibonacci(int n) {
		if(n == 1 || n == 0)
			return n;
		else
			return fibonacci(n-1) + fibonacci(n-2);
	}

}
