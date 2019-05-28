import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 TimerTask task = new TimerTask() {
		        public void run() {
		            System.out.println("Task performed on: " + new Date() + "n" +
		              "Thread's name: " + Thread.currentThread().getName());
		        }
		        
		    };
		    Timer timer = new Timer("Timer");
		    System.out.println(new Date()); 
		    long delay = 10000;
		    timer.schedule(task, delay);
		    System.out.println("hi");
	}

}
