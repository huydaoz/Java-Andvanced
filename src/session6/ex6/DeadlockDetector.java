package session6.ex6;
import java.lang.management.*;

public class DeadlockDetector implements Runnable {

    @Override
    public void run() {

        ThreadMXBean bean =
                ManagementFactory.getThreadMXBean();

        while (true) {

            long[] ids = bean.findDeadlockedThreads();

            if (ids != null) {

                System.out.println(
                        "DEADLOCK DETECTED!"
                );

                ThreadInfo[] infos =
                        bean.getThreadInfo(ids);

                for (ThreadInfo info : infos) {
                    System.out.println(info);
                }
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}