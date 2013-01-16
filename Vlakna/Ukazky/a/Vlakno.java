package a;

import java.util.Random;

public class Vlakno extends Thread {
	boolean condition = true;
	int i = 0;

	public Vlakno(String name) {
		super(name); // set name of thread ( setName( String name ) )
	}

	public void run() {
		while (condition) { // Endless cycle of waiting for the end conditions.
			TestIfWillBeEndAndSetCondition();
			SleepMe();
			System.out.println(this.getName() + ": " + i);
			i++;
		}
	}

	private void SleepMe() {
		try {
//			Thread.sleep(this.GetRandomSleepTime());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private int GetRandomSleepTime() {
		int timeToSleep = 0;
		Random random = new Random();
		timeToSleep = Math.abs(random.nextInt(5000));

		return timeToSleep;
	}

	private void TestIfWillBeEndAndSetCondition() {
		if (i == 10)
			this.condition = false;
	}

}
