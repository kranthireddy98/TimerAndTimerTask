import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        TimerTask cleanUpTImerTask = new CleanUpTimeTask();
        TimerTask DbOperations = new DbOperations();
        Timer timer = new Timer(true); // Daemon timer -- Schedule Task on Application Shutdown
        Timer timer2 = new Timer();
        Date scheduleDateTime = new Date(System.currentTimeMillis() + 20000);

        System.out.println("scheduleDateTime = " + scheduleDateTime);

        //One-Time Execution
        timer.schedule(cleanUpTImerTask, 5000); // Executes after 5 Seconds

        // Schedule Task at a Specific Date/Time
        timer.schedule(cleanUpTImerTask, scheduleDateTime);

        //Fixed-Delay Execution
        timer.schedule(cleanUpTImerTask, 0, 5000);// Executes every 5 seconds

        //Fixed-Rate Execution Schedule the task to run repeatedly
        timer.scheduleAtFixedRate(DbOperations, 0, 30000);// Executes every 5 seconds

        //Cancel a Scheduled Task
        timer.schedule(cleanUpTImerTask, 5000);
        timer.cancel(); // Cancels all scheduled tasks in the timer

        System.out.println("Timer has Schedule the ... cleanUpTimerTask");
    }
}