package b;

public class Tak extends Orloj implements Runnable {
	public void run() {
		try{
			while (true) {
				System.out.println("Tak"+this.ShowRandomNumber());
				Thread.sleep(1000);
			}
		}
		catch (InterruptedException ex ) {
			
		}
	}

}
